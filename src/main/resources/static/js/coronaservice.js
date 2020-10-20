const coronaservice = (() =>{
    const getAllCases = (callback) =>{
        axios.get('/api/cases').then(res=>{
            callback(res);
        })
    }
    const getCasesByCountry = (country,callback) =>{
        axios.get('/api/countries?country=' + country).then(res=>{
            callback(res);
        })
    }
    return{
        getCasesByCountry:getCasesByCountry,
        getAllCases:getAllCases
    }
})();
