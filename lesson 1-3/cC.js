var fruits = ["kiwi", "rambutan", "mango", "tomato"];
fruits.pop();
console.log(fruits);
fruits.push("gooseberry");console.log(fruits);
console.log(fruits);
fruits.shift();
console.log(fruits);
fruits.unshift("banana");
console.log(fruits);

//slice will get a number of elements starting at the start position but will not change the array.

//splice will return the elements that are removed and change the array.

fruits.slice(0, 2);
fruits.splice(0,2);
console.log(fruits)
fruits[fruits.length] = "blueberry";

fruits[6] = "pineapple";
console.log(fruits);
