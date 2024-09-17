
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>KALKULATOR WPFK</title>
       
    </head>

    <body>
        <h1>MUHAMMAD FADLI ABDUL AZIZ</h1>
        <div class="calculator">
            <input type="text" id="hasil" disabled>
            <br>
            <button onclick="clearResult()">C</button>
            <button onclick="appendValue('1')">1</button>
            <button onclick="appendValue('2')">2</button>
            <button onclick="appendValue('3')">3</button>
            <br>
            <button onclick="appendValue('+')">+</button>
            <button onclick="appendValue('4')">4</button>
            <button onclick="appendValue('5')">5</button>
            <button onclick="appendValue('6')">6</button>
            <br>
            <button onclick="appendValue('-')">-</button>
            <button onclick="appendValue('7')">7</button>   
            <button onclick="appendValue('8')">8</button>
            <button onclick="appendValue('9')">9</button>
            <br>
            <button onclick="calculateResult()">=</button>
            <button onclick="appendValue('*')">*</button>
            <button onclick="appendValue('0')">0</button>
            <button onclick="appendValue('/')">/</button>

        </div>

        <script>
            function appendValue(value) {
                document.getElementById("hasil").value += value;
            }

            function clearResult() {
                document.getElementById("hasil").value = "";
            }

            function calculateResult() {
                try {
                    let result = eval(document.getElementById("hasil").value);
                    document.getElementById("hasil").value = result;
                } catch (e) {
                    document.getElementById("hasil").value = "Tidak bisa";
                } 
            }
            

           
            
        </script>

    </body>

    <style>

        h1 {
            position: absolute;
            text-align: center;
            top: 20px;
            font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
        }

        body {
            justify-content: center;
            background-color: rgb(255, 255, 255);
            align-items: center;
            height: 100vh;
            margin: 0;
            display: flex;
        }

        .calculator {
            background-color: rgb(243, 242, 240);
            padding: 20px;
            border-radius: 20px;
            box-shadow: 0 0 10px rgb(58, 64, 80);

        }

        button {
            width: 75px;
            height: 40px;
            font-size: 15px;
            margin: 1px;
            color: rgb(8, 3, 0);
            background-color: rgb(255, 255, 255);
            border-radius: 50px;
            cursor: pointer;
             
        }   

        #hasil {
        width: 100%;
        height: 50px;
        font-size: 24px;
        text-align: right;
        margin-bottom: 10px;
        padding-right: 10px;
        border-radius: 15px;
        justify-content: center;
    }

    </style>
    
</html>
