const questions = [
{
    question: "Which language is used for web development?",
    options: ["Python", "HTML", "Java", "C++"],
    answer: 1
},
{
    question: "Who developed Java?",
    options: ["James Gosling", "Elon Musk", "Bill Gates", "Mark"],
    answer: 0
}
];

let currentQuestion = 0;

function loadQuestion(){

    document.getElementById("question").innerText =
        questions[currentQuestion].question;

    document.getElementById("op1").innerText =
        questions[currentQuestion].options[0];

    document.getElementById("op2").innerText =
        questions[currentQuestion].options[1];

    document.getElementById("op3").innerText =
        questions[currentQuestion].options[2];

    document.getElementById("op4").innerText =
        questions[currentQuestion].options[3];
}

function checkAnswer(index){

    if(index === questions[currentQuestion].answer){
        alert("Correct Answer");
    } else {
        alert("Wrong Answer");
    }
}

function nextQuestion(){

    currentQuestion++;

    if(currentQuestion < questions.length){
        loadQuestion();
    } else {
        alert("Quiz Finished");
    }
}

loadQuestion();