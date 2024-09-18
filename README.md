<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>KALKULATOR WPFK</title>
    </head>

    <body>
        <div id="sidebar" class="sidebar">
            <h2>Rumus</h2>
            <ul>
                <li><strong>Segitiga</strong> <br> Luas = ½ × Alas × Tinggi</li>
                <li><strong>Persegi</strong> <br> Luas = Sisi × Sisi</li>
                <li><strong>Persegi Panjang</strong> <br> Luas = Panjang × Lebar</li>
            </ul>
            <button class="closebtn" onclick="closeSidebar()">×</button>
        </div>

        <div id="main-content">
            <div class="calculator-container">
                <button class="openbtn" onclick="openSidebar()">☰ Rumus</button>
                <h1>MUHAMMAD FADLI ABDUL AZIZ</h1>
                <div class="calculator">
                    <input type="text" id="hasil" disabled>
                    <div class="buttons">
                        <button class="clear" onclick="clearResult()">C</button>
                        <button onclick="appendValue('1')">1</button>
                        <button onclick="appendValue('2')">2</button>
                        <button onclick="appendValue('3')">3</button>
                        <button onclick="appendValue('+')">+</button>

                        <button onclick="appendValue('4')">4</button>
                        <button onclick="appendValue('5')">5</button>
                        <button onclick="appendValue('6')">6</button>
                        <button onclick="appendValue('-')">-</button>

                        <button onclick="appendValue('7')">7</button>
                        <button onclick="appendValue('8')">8</button>
                        <button onclick="appendValue('9')">9</button>
                        <button onclick="appendValue('*')">*</button>

                        <button onclick="appendValue('0')">0</button>
                        <button onclick="appendValue('/')">/</button>
                        <button class="delete" onclick="deleteLast()">DEL</button>
                        <button class="equal" onclick="calculateResult()">=</button>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function appendValue(value) {
                document.getElementById("hasil").value += value;
            }

            function clearResult() {
                document.getElementById("hasil").value = "";
            }

            function deleteLast() {
                let currentValue = document.getElementById("hasil").value;
                document.getElementById("hasil").value = currentValue.slice(0, -1);
            }

            function calculateResult() {
                try {
                    let result = eval(document.getElementById("hasil").value);
                    document.getElementById("hasil").value = result;
                } catch (e) {
                    document.getElementById("hasil").value = "Error";
                }
            }

            function openSidebar() {
                document.getElementById("sidebar").style.width = "250px";
                document.getElementById("main-content").style.marginLeft = "250px";
            }

            function closeSidebar() {
                document.getElementById("sidebar").style.width = "0";
                document.getElementById("main-content").style.marginLeft = "0";
            }
        </script>
    </body>

    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Arial', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f9;
        }

        /* Sidebar styling */
        .sidebar {
            height: 100%;
            width: 0;
            position: fixed;
            top: 0;
            left: 0;
            background-color: #111;
            overflow-x: hidden;
            transition: 0.5s;
            padding-top: 60px;
            z-index: 1;
        }

        .sidebar h2 {
            padding-left: 25px;
            font-size: 22px;
            color: white;
        }

        .sidebar ul {
            padding-left: 25px;
            list-style-type: none;
            color: white;
        }

        .sidebar ul li {
            margin: 15px 0;
            font-size: 18px;
        }

        .sidebar .closebtn {
            position: absolute;
            top: 0;
            right: 10px;
            font-size: 36px;
            margin-left: 50px;
            background: none;
            color: white;
            border: none;
            cursor: pointer;
        }

        #main-content {
            transition: margin-left 0.5s;
            padding: 16px;
        }

        .openbtn {
            background-color: #111;
            color: white;
            padding: 10px 15px;
            font-size: 20px;
            cursor: pointer;
            border: none;
            margin-bottom: 20px;
            border-radius: 5px;
        }

        .openbtn:hover {
            background-color: #444;
        }

        .calculator-container {
            text-align: center;
        }

        h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }

        .calculator {
            background-color: #fff;
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            max-width: 300px;
            margin: auto;
        }

        #hasil {
            width: 100%;
            height: 50px;
            font-size: 24px;
            text-align: right;
            padding-right: 10px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);
        }

        .buttons {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            grid-gap: 10px;
        }

        button {
            height: 50px;
            font-size: 18px;
            border: none;
            border-radius: 10px;
            background-color: #f1f1f1;
            color: #333;
            cursor: pointer;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }

        button:hover {
            background-color: #ddd;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        button:active {
            background-color: #ccc;
        }

        .clear {
            background-color: #ff6b6b;
            color: white;
        }

        .clear:hover {
            background-color: #ff4a4a;
        }

        .equal {
            background-color: #4caf50;
            color: white;
            grid-column: span 2;
        }

        .equal:hover {
            background-color: #45a049;
        }
    </style>
</html>
