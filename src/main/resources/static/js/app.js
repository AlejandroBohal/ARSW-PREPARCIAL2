const app = (() =>{
    let receivedCases = []
    let casesByCountry = []
    var map;
    var markers;
    var bounds;
    const mapCasesToObjects = (cases) =>{
        let table = $("#tabla > tbody");
        table.empty();
        receivedCases = cases.data.map(({country,deaths,confirmed,recovered}) =>({
            country:country,
            deaths:deaths,
            confirmed:confirmed,
            recovered: recovered
        }))
        receivedCases.forEach(({country, deaths, confirmed,recovered}) => {
            table.append(
                `<tr> 
                      <td><a data-toggle=tab href=#TabCountry onclick=app.getStatsCountry('${country}')>${country}</a></td>
                      <td>${country}</td>
                      <td>${deaths}</td>
                      <td>${confirmed}</td>
                      <td>${recovered}</td>
                </tr>`
            );
        })
    }
    const mapCasesById = (cases) =>{
        let table = $("#casesByCountry > tbody");
        console.log(cases);
        table.empty();
        casesByCountry = cases.data.map(({province,deaths,confirmed,recovered,localization}) =>({
            province: province,
            deaths:deaths,
            confirmed:confirmed,
            recovered:recovered,
            localization:localization
        }))
        casesByCountry.forEach(({province,deaths,confirmed,recovered}) => {
            table.append(
                `<tr> 
                      <td>${province}</td>
                      <td>${deaths}</td>
                      <td>${confirmed}</td>
                      <td>${recovered}</td>
                </tr>`
            );
        })
        initMap(casesByCountry);
    }
    const getAllCases = () =>{
        coronaservice.getAllCases(mapCasesToObjects);
    }
    const getStatsByCountry = (country) =>{
        coronaservice.getCasesByCountry(country,mapCasesById);
    }

    const initMap = (cases) =>{
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -34.397, lng: 150.644},
            zoom: 8
        });
        plotMarkers(cases);
    }
    const plotMarkers = (m) => {
        console.log(m);
        markers = [];
        bounds = new google.maps.LatLngBounds();
        m.forEach(function (marker) {
            var position = new google.maps.LatLng(marker.localization.lat, marker.localization.lng);
            markers.push(
                new google.maps.Marker({
                    position: position,
                    map: map,
                    animation: google.maps.Animation.DROP
                })
            );
            bounds.extend(position);
        });
        map.fitBounds(bounds);
    }
    return{
        getAllCases:getAllCases,
        getStatsCountry:getStatsByCountry
    }
})();
