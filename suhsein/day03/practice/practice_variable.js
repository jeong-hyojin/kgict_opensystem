// -- 1. var 재선언 가능
// 재선언이 가능하면 위험 -> ES6 에서는 var 쓰지 않고, let, const 쓰는 것을 권장한다.

var x = 10;
var x = 20; 
console.log(x);


// -- 2. let 재선언 불가

// let y = 10;
// let y = 20;
// y = 20; // 재할당은 가능
// console.log(y);

// -- 3. const 재선언, 재할당 불가

// const z = 10;
// const z = 20;
// z = 20;
// console.log(z);

