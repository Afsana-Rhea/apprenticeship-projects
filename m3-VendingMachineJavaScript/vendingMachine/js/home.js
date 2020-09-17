var ds = new DataService();

function formatItem(item) {
        
    return     `<div class="col-md-4">
                    <div class="item">
                        <h3>${item.name}</h3>
                        <h4>${item.price}</h4>
                        <h5>${item.quantity}</h5>
                        <button class="btn btn-danger btnSelect" data-itemId='${item.id}'>>Select</button>
                    </div>
                </div>`;

            
}
function refreshItems(items) {
    $("#items").empty();
    let itemDiv = $("#items");
    items.forEach(item => {
        itemDiv.append(formatItem(item));
    });
}
function onSelectClick(event) {

    event.preventDefault();
    let itemId = $(this).attr('data-itemId');
    $("#txtItemId").val(itemId);
}
function onVendItemClick(event) {
    event.preventDefault();
    let itemId = $("#txtItemId").val();
    let money = $("#txtBalance").val();
    ds.vendItem(money, itemId, onSuccess, onError );
    
}
function onReturnChangeClick(event) {
    event.preventDefault();
    $("#txtMessage").val("");

}

function onSuccess(response){
    let change = "quarters: " + response.quarters + " dimes:" + response.dimes + " nickels: " + response.nickels + " pennies: " + response.pennies ;
    $("#txtMessage").val(change);
    let itemId = $("#txtItemId").val("0");
    let money = $("#txtBalance").val("0");
    ds.readAll(refreshItems);
   

     }

     function onError(response){
         $("#txtMessage").val(response.responseJSON.message);
         }

function onMoneyClick(event) {
    event.preventDefault();
    let textBox = $("#txtBalance").val();
    let money = $(this).attr('data-amount');
    let total = Number(textBox) + Number(money);
    total = total.toFixed(2);
    $("#txtBalance").val(total);
}



$(document).ready(function () {
    $(document).on("click", ".btnSelect", onSelectClick);
    $(document).on("click", "#btnVendItem", onVendItemClick);
    $(document).on("click", "#btnReturnChange", onReturnChangeClick);
    $(document).on("click", ".btnAddChange", onMoneyClick);
    ds.readAll(refreshItems);

});