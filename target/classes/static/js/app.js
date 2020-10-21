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
                `<tr data-toggle=tab href=#TabCountry onclick=app.getStatsCountry('${country}','${deaths}','${confirmed}','${recovered}') style="cursor: pointer"> 
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
    const fillTableStats = (country,deaths,confirmed,recovered) =>{
        let table = $("#case > tbody");
        table.empty()
        table.append(
            `<tr> <td>Country</td> <td>${country}</td> </tr>
             <tr> <td>Deaths</td> <td>${deaths}</td> </tr>
             <tr> <td>Infected</td><td>${confirmed}</td></tr>
             <tr> <td>Recovered</td> <td>${recovered}</td></tr>  
            `
        );

    }
    const getStatsByCountry = (country,deaths,confirmed,recovered) =>{
        fillTableStats(country,deaths,confirmed,recovered);
        coronaservice.getCasesByCountry(country,mapCasesById);
    }
    const initMap = (cases) =>{
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -34.397, lng: 150.644},
            zoom: 0
        });
        plotMarkers(cases);
    }
    const plotMarkers = (m) => {
        console.log(m);
        markers = [];
        bounds = new google.maps.LatLngBounds();
        m.forEach(function (marker) {
            var position = new google.maps.LatLng(marker.localization.lat, marker.localization.lng);
            console.log(position);
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
