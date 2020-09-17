
var rolls;
var maxMoney;
var rollsAtMax;
var gameMoney;
function luckySevens() {
  var bet = Number(document.getElementById("startBet").value);
  rolls = 0;
  gameMoney = bet;
  maxMoney= bet;
  rollsAtMax = 0;
  if (gameMoney <= 0) {
    alert("ERROR");
  }
  else {
    while (gameMoney>0) {
      rolls++
      if(rollDice() == 7){
        gameMoney += 4;
        if (gameMoney > maxMoney){
          maxMoney += gameMoney;
          rollsAtMax = rolls;
        }
        console.log("You Win");
      }
      else {
        gameMoney--;
        console.log("You Lose");
      }
  }
}
console.log(bet)
document.getElementById("results").style.display= "block";
document.getElementById("startingBet").innerText= bet;
document.getElementById("maxMoney").innerText = maxMoney;
document.getElementById("rollsAtMax").innerText = rollsAtMax;
document.getElementById("rollsNum").innerText = rolls;

return false;
}

function rollDice(gameMoney){
  //for (var i = gameMoney; i > 0; i--) {
  var rolled = Math.floor(Math.random() *6) + 1;
  var rolled2 = Math.floor(Math.random() *6) + 1;
  return rolled + rolled2;
//}
}
