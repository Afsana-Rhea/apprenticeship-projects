function DataService(){
    let self = this;
    self.readAll = function getAllItems(callback, errorCallback){
        $.ajax({
            url: " https://tsg-vending.herokuapp.com/items",
            method: "GET",
            headers: {
                "content-type": "application/json",
                "accept": "application/json"
            },
            success: callback,
            error:errorCallback
        });

    }


    self.vendItem = function vendItems(money, itemId, callback, errorCallback){
        $.ajax({
            url: "http://tsg-vending.herokuapp.com/money/" + money + "/item/" + itemId,
            method: "POST",
            headers: {
                "content-type": "application/json",
                "accept": "application/json"
            },
            data: JSON.stringify(money),
            success: callback,
            error: errorCallback
        });

    }
}



