
function countingCharacters2(stringToCount, characterToFind){
var characterCount = 0;
for (var characterPosition=0;
  characterPosition < stringToCount.length;
  characterPosition++){
if (stringToCount[characterPosition] ==characterToFind){


    characterCount++
  }
}

console.log("String to search in:" + stringToCount);
console.log("Character to find: " + characterToFind);
console.log("Number of times the character appears: "+ characterCount);
}

function countingCharacters3(str, search){
var count = 0;
var numChars = search.length;
var lastIndex = str.length - numChars;
for  (var index=0; index <= lastIndex;index++){
var current = str.substring(index, index + numChars);
if(current ==search){
  count++;
}

}


return count;
}

function addTwoNumbers(firstNumber, secondNumber){
  var sum= firstNumber + secondNumber;
return sum;

}

function rollDice(numSides){
return Math.floor(Math.random() * numSides) + 1;
}

var friends = ["Aneki", "Quell", "Clarity", "Sleep", "Roghar", "DM Crimson"];
var team1 = new Array();
var team2 = new Array();

for (var i = 0; i < friends.length; i++){
if (i % 2 == 0){
  team1.push(friends[i]);
   } else {
  team2.push(friends[i]);
}
}

console.log(team1)

for (var i= 0; i<friends.length; i++){
if (1 % 2 ==  0){
team1.unshift(friends[i]);
} else {
  team2.unshift(friends[i]);
}
}

console.log(team1)
