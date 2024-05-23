package com.example.wordle;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView[][] letterBoxes = new TextView[5][5]; // 5 intentos (filas) y 5 letras por intento (columnas)
    private int currentRow = 0;
    private int currentColumn = 0;
    private String correctWord;
    List<String> wordList = Arrays.asList(
            "PERRO", "CIELO", "AVION", "BARCO", "ARBOL", "NIEVE", "LAPIZ", "LLAVE", "CINCO", "TRIPO",
            "CLASE", "LUCHA", "DULCE", "GENTE", "JUEGO", "VIDEO", "TIENE", "MOTOR", "PISTA", "MUNDO",
            "CIEGA", "CLIMA", "BOLSA", "BUENO", "CANSO", "CIEZA", "CORTE", "CUBRE", "CUIDA", "LUNES",
            "PERAS", "PIZZA", "VIVIR", "CAMPO", "CALLE", "SUELO", "FUEGO", "HOJAS", "AMIGO", "BEBER",
            "CIEGO", "CORAZ", "FELIZ", "GRANO", "HIELO", "JUGAR", "LIMON", "MANOS", "NAVES", "OCASO",
            "PALMA", "QUESO", "RAMPA", "SELVA", "TIGRE", "UNION", "VIUDA", "ZORRO", "ARADO", "BRAVO",
            "DIVAN", "PAPAS", "FRESA", "GATOS", "HOGAR", "JAULA", "KIWIS", "LECHE", "MARCO", "NIÑOS",
            "OLIVO", "PATIO", "RATON", "SALTO", "TEMPO", "USADO", "VERBO", "YERBA", "ZAFIR", "FONDO",
            "PARIS", "VERDE", "PUNTO", "SUAVE", "BICHO", "NUBES", "PIEZA", "RIEGO", "PLANO", "GRIFO",
            "ZUECO", "RUMBO", "TURNO", "JOVEN", "ANIMO", "TAPIZ", "RITMO", "MUECA", "MIRAR", "PODER",
            "SALSA", "TURCO", "VASOS", "VISTO", "YOGUR", "TRECE", "TORRE", "RUEDA", "VALOR", "REINA",
            "FIRME", "TRIGO", "BRISA", "GUITA", "HONOR", "DONDE", "VIRUS", "TIJER", "JARRA", "ROLLO",
            "AGUAS", "BALON", "CAJON", "DEDOS", "ESCOL", "FLORO", "GORRA", "HELLO", "IMPAR", "JUNTO",
            "KILOS", "LUCES", "MAYOR", "NUEVO", "OPERA", "PERAS", "QUESO", "RESTA", "SUELO", "TORRE",
            "UVASO", "VECES", "YACIA", "ZAPAS", "AFINA", "BANDO", "CEBRA", "DOBLE", "ESCAR", "FALLO",
            "GOLPE", "HUMOS", "INDIA", "JUNTA", "KOALA", "LETRA", "MANCO", "NEGRO", "ORINO", "PLATO",
            "QUIEN", "RAYON", "SALVO", "TOMAR", "USUAL", "VIVIR", "YOGUR", "ZONAS", "ACTOR", "CABRA",
            "DINAR", "ECHAR", "FLOTA", "GOLFO", "HUECO", "IDEAL", "JAQUE", "LUGAR", "MELON", "NOVIO",
            "OROLO", "PLENA", "QUEJA", "RATON", "SILLA", "TENOR", "URANO", "VARON", "YERMO", "ZURDO",
            "ACERA", "BURRO", "CIEGA", "DOPAR", "ESCAR", "FALSA", "GRAVE", "HORAS", "IDEAS", "JARRA",
            "LANZA", "METRO", "NADAR", "OCUPA", "PELOS", "RIEGO", "SALIR", "TERMO", "UNOJO", "VALLE",
            "ZONAL", "BARCO", "CAMPO", "DEMOS", "ENERO", "FUEGO", "GRITO", "HABER", "INTER", "JOVEN",
            "LLAVE", "MAFIA", "NIEVE", "OBRAS", "PANAL", "QUITO", "ROSAS", "SUDAR", "TANTO", "UÑERO",
            "VISTA", "YOGUR", "ZORRO", "APICE", "CABAL", "DONAR", "EVITA", "FRUTO", "GASTO", "HILOS",
            "IGUAL", "JUNTO", "LABIO", "MIRAR", "NOBLE", "OLVID", "POEMA", "QUESO", "RADIO", "SANOA",
            "TARDE", "URGEN", "VENDA", "ZOCALO", "ANGEL", "BOCAO", "CENAR", "DADOS", "ESCAR", "FUSIL",
            "GORDO", "HERIR", "ISLAS", "JAZON", "LLAMA", "MUJER", "NACER", "OCIOS", "PAPEL", "QUEJA",
            "RAZON", "SUCIO", "TECHO", "UTILO", "VALOR", "YOGUI", "ZORRO", "ALERO", "CARNE", "DERRO",
            "EQUIP", "FUTUO", "GOLFO", "HUEVO", "ISLAM", "JUANA", "LUZON", "MEDIO", "NORMA", "OTROS",
            "PELOS", "RAMOS", "SIETE", "TURNO", "UNOJO", "VERDE", "YERMO", "ZONAL", "ACERO", "BAILE",
            "CLARA", "DONDE", "ENANO", "FRUTA", "GATOS", "HECHO", "IDEAR", "JUGAR", "LLANO", "MECHA",
            "NACER", "OVINO", "PESAR", "QUESO", "REDON", "SALTO", "TONO", "UVASO", "VENTA", "ZAFAR",
            "AZOTE", "CARRO", "DUCHA", "ENSAY", "FUERA", "GRAMA", "HUNIR", "IDEAL", "JUSTO", "LIBRA",
            "MAYOR", "OBVIO", "PARES", "QUEJA", "REINA", "SUERO", "TODOS", "UÑIDO", "VIOLA", "YOGUR",
            "ZAFAR", "ACTOR", "CABRA", "DENSA", "EMOJI", "FIERA", "GOLPE", "HOGAR", "IMPAR", "JUNTA",
            "LABIA", "MENOS", "NOVEL", "OTROS", "POETA", "ROCES", "SOBRE", "TUTOR", "UNICO", "VALOR",
            "YACER", "ZORRO", "APICE", "CASAS", "DONAR", "ERGUI", "FRUTO", "GIRAR", "HIELO", "IMAGEN",
            "JUNTO", "LACRA", "MUELA", "NEGRO", "ORDEN", "PANEL", "QUIZA", "RISAS", "SUDAR", "TROZO",
            "VECES", "YOGUR", "ZONAL", "BUENO", "CALMA", "DURAR", "EROSO", "FUERA", "GOLPE", "HONDO",
            "IDEAL", "JUSTA", "LIMON", "MUERO", "NUEVO", "OSADO", "PIEZA", "QUISO", "RASTO", "SIEMO",
            "TENER", "URANO", "VIENE", "ZAFIR", "ALIEN", "CARRO", "DUCHA", "ECHAR", "FOCAS", "GUSTO",
            "HUEVO", "IMPOS", "JUEGO", "LUCHA", "MARZO", "OCUPA", "PATIO", "QUIZA", "ROMER", "SALTO",
            "TRIBU", "VACAS", "YACER", "ZONAL", "BARCO", "CALOR", "DORES", "FLAMA", "GOLPE", "HONDO",
            "ISLAO", "JUSTO", "LIMAR", "MUCHO", "NUNCA", "OTRAO", "PISTA", "QUEJA", "RAYON", "SIENA",
            "UNIDO", "VECES", "YOGUR", "ZONAL", "AFANO", "CANAL", "DOLAR");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        initializeLetterBoxes(gridLayout);
        initializeKeyboard();
        startNewGame();
    }

    private void initializeLetterBoxes(GridLayout gridLayout) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String letterBoxID = "letter" + (i * 5 + j + 1);
                int resID = getResources().getIdentifier(letterBoxID, "id", getPackageName());
                letterBoxes[i][j] = findViewById(resID);
            }
        }
    }

    private void initializeKeyboard() {
        View.OnClickListener keyboardClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Button button = (Button) v;
                    String letter = button.getText().toString();
                    if (currentColumn < 5) {
                        letterBoxes[currentRow][currentColumn].setText(letter);
                        currentColumn++;
                    }
                } catch (Exception e) {
                    Log.e("Wordle", "Error al presionar el teclado: " + e.getMessage());
                }
            }
        };

        setKeyboardClickListener(R.id.buttonQ, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonW, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonE, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonR, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonT, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonY, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonU, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonI, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonO, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonP, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonA, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonS, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonD, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonF, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonG, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonH, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonJ, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonK, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonL, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonZ, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonX, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonC, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonV, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonB, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonN, keyboardClickListener);
        setKeyboardClickListener(R.id.buttonM, keyboardClickListener);

        findViewById(R.id.buttonDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentColumn > 0) {
                    currentColumn--;
                    letterBoxes[currentRow][currentColumn].setText("");
                }
            }
        });

        findViewById(R.id.buttonEnter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Verificar la palabra ingresada
                    if (currentColumn == 5) {
                        checkWord();
                    }
                } catch (Exception e) {
                    Log.e("Wordle", "Error al verificar la palabra: " + e.getMessage());
                }
            }
        });

        Button resetButton = findViewById(R.id.restartButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void setKeyboardClickListener(int buttonId, View.OnClickListener listener) {
        findViewById(buttonId).setOnClickListener(listener);
    }

    private void checkWord() {
        try {
            StringBuilder guess = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                guess.append(letterBoxes[currentRow][i].getText().toString());
            }

            if (wordList.contains(guess.toString())) {
                for (int i = 0; i < 5; i++) {
                    char guessedChar = guess.charAt(i);
                    char correctChar = correctWord.charAt(i);
                    if (guessedChar == correctChar) {
                        letterBoxes[currentRow][i].setBackgroundColor(Color.GREEN);
                        updateKeyboardColor(guessedChar, Color.GREEN);
                    } else if (correctWord.contains(String.valueOf(guessedChar))) {
                        letterBoxes[currentRow][i].setBackgroundColor(Color.YELLOW);
                        updateKeyboardColor(guessedChar, Color.YELLOW);
                    } else {
                        letterBoxes[currentRow][i].setBackgroundColor(Color.GRAY);
                        updateKeyboardColor(guessedChar, Color.GRAY);
                    }
                }

                if (guess.toString().equals(correctWord)) {
                    Toast.makeText(this, "¡Felicidades! Has adivinado la palabra.", Toast.LENGTH_LONG).show();
                } else {
                    currentRow++;
                    currentColumn = 0;
                    if (currentRow == 5) {
                        Toast.makeText(this, "Lo siento, no has adivinado la palabra. La palabra correcta era: " + correctWord, Toast.LENGTH_LONG).show();
                        resetGame();
                    }
                }
            } else {
                Toast.makeText(this, "Palabra no válida.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("Wordle", "Error al comprobar la palabra: " + e.getMessage());
        }
    }

    private void updateKeyboardColor(char letter, int color) {
        try {
            String buttonID = "button" + letter;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            Button button = findViewById(resID);
            button.setBackgroundColor(color);
        } catch (Exception e) {
            Log.e("Wordle", "Error al actualizar el color del teclado: " + e.getMessage());
        }
    }

    private void resetGame() {
        try {
            currentRow = 0;
            currentColumn = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    letterBoxes[i][j].setText("");
                    letterBoxes[i][j].setBackgroundColor(Color.parseColor("#E0E0E0")); // Color original de fondo
                }
            }
            resetKeyboardColors();
            startNewGame();
        } catch (Exception e) {
            Log.e("Wordle", "Error al reiniciar el juego: " + e.getMessage());
        }
    }

    private void resetKeyboardColors() {
        int[] keyboardButtonIds = {
                R.id.buttonQ, R.id.buttonW, R.id.buttonE, R.id.buttonR, R.id.buttonT, R.id.buttonY,
                R.id.buttonU, R.id.buttonI, R.id.buttonO, R.id.buttonP, R.id.buttonA, R.id.buttonS,
                R.id.buttonD, R.id.buttonF, R.id.buttonG, R.id.buttonH, R.id.buttonJ, R.id.buttonK,
                R.id.buttonL, R.id.buttonZ, R.id.buttonX, R.id.buttonC, R.id.buttonV, R.id.buttonB,
                R.id.buttonN, R.id.buttonM
        };
        for (int id : keyboardButtonIds) {
            Button button = findViewById(id);
            button.setBackgroundColor(Color.LTGRAY); // Color original de los botones del teclado
        }
    }

    private void startNewGame() {
        correctWord = wordList.get(new Random().nextInt(wordList.size()));
    }

    private void addNewWord(String newWord) {
        for (int i = 0; i < 5; i++) {
            if (letterBoxes[0][i].getText().toString().isEmpty()) {
                letterBoxes[0][i].setText(newWord);
                break;
            }
        }
    }
}
