package com.example.janillo.simonbis;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //Establecemos las variables que se usarán tanto en el Main
    //como en la clase Parpadeo
    public int[] botones = {R.id.bRojo, R.id.bAzul, R.id.bVerde, R.id.bAmarillo};
    public int[] secuencia;
    public static Handler manejador = new Handler();
    public static Runnable ejecucion;
    public int numeroAleatorio;
    public int contador = 0;
    public static int numeroVueltas = 4;
    public Button miBoton;
    public static long tiempoEjecucion = 2000;
    int vueltaVerificacion = 0;
    public MediaPlayer mpacierto = null;
    public MediaPlayer mpfallo = null;
    int fallos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Estos dos archivos son los de música que se reproducirá
        mpacierto = MediaPlayer.create(this, R.raw.acierto);
        mpfallo = MediaPlayer.create(this, R.raw.fallo);

        //Runnable encargado de enfocar los botones durante X segundos
        ejecucion = new Runnable() {
            @Override
            public void run() {
                if (contador < numeroVueltas) {
                    //Generamos un número aleatorio de 0-3 para asignar a un botón
                    numeroAleatorio = (int) (Math.random() * 4 + 0);
                    //Guardamos la secuencia que se crea para verificarla más adelante
                    secuencia[contador] = botones[numeroAleatorio];
                    miBoton = (Button) findViewById(botones[numeroAleatorio]);
                    //LLama al método que focaliza botones
                    Parpadeo.parpadear(miBoton);
                    //el Handler hará que se ejecute de seguido hasta que se le diga lo contrario
                    manejador.postDelayed(ejecucion, tiempoEjecucion);
                    //Reproducimos la música
                    mpacierto.start();
                    contador++;
                } else {
                    manejador.removeCallbacks(ejecucion);
                }
            }
        };

    }

    public void salir() {
        System.exit(0);
    }

    //Da comienzo a la ejecución de la secuencia de juego
    //llamando al runnable
    public void jugar(View v) {
        contador = 0;
        fallos = 0;
        ejecucion.run();
    }

    //Este método establece la velocidad y el número de repeticiones dle ciclo de juego
    //guarda en una variable el botón de nivel pulsado
    public void ponNivel(View v) {
        fallos = 0;
        Button botonNivel = (Button) findViewById(v.getId());
        //Dependiendo del botón estabecerá unos parámetros de juego
        switch (v.getId()) {
            case R.id.bnivel1:
                Parpadeo.tiempoParpadeo = 1500;
                tiempoEjecucion = 2000;
                numeroVueltas = 4;
                secuencia = new int[numeroVueltas];
                vueltaVerificacion = 0;
                break;
            case R.id.bnivel2:
                Parpadeo.tiempoParpadeo = 1000;
                tiempoEjecucion = 1500;
                numeroVueltas = 5;
                secuencia = new int[numeroVueltas];
                vueltaVerificacion = 0;
                break;
            case R.id.bnivel3:
                Parpadeo.tiempoParpadeo = 500;
                tiempoEjecucion = 1000;
                numeroVueltas = 6;
                secuencia = new int[numeroVueltas];
                vueltaVerificacion = 0;
                break;
            case R.id.bnivel4:
                Parpadeo.tiempoParpadeo = 300;
                tiempoEjecucion = 700;
                numeroVueltas = 7;
                secuencia = new int[numeroVueltas];
                vueltaVerificacion = 0;
                break;
            case R.id.bnivel5:
                Parpadeo.tiempoParpadeo = 200;
                tiempoEjecucion = 500;
                numeroVueltas = 8;
                secuencia = new int[numeroVueltas];
                vueltaVerificacion = 0;
                break;
            default:
                Parpadeo.tiempoParpadeo = 1500;
                tiempoEjecucion = 2000;
                secuencia = new int[4];
                numeroVueltas = 4;
                vueltaVerificacion = 0;
                break;
        }

    }

    //Método que verifica si la secuencia introducida coincide con la generada aleatoriamente
    public void verificar(View v) {

        if (vueltaVerificacion >= secuencia.length) {
            toastie();
        }
        else{
            if (v.getId() == secuencia[vueltaVerificacion]) {
                mpacierto.start();
                vueltaVerificacion++;
                //Verificamos cuantas vueltas ha dado ya
                //Para que no estalle por la longitud del array de verificación
                if (vueltaVerificacion >= secuencia.length) {
                    toastie();
                }
            } else {
                //Verifica la posición de la secuencia creada con el botón pulsado
                //Si acierta avanza el contador
                //De lo contrario añade un fallo
                mpfallo.start();
                vueltaVerificacion++;
                fallos++;
                //Verificamos cuantas vueltas ha dado ya
                //Para que no estalle por la longitud del array de verificación
                if (vueltaVerificacion >= secuencia.length) {
                    toastie();
                }


            }
        }



    }
    //Imprime un mensaje dependiendo del resultado de la secuencia introducida
    public void toastie(){
        if (fallos > 0) {
            mpfallo.start();
            Toast.makeText(this, "Has perdido", Toast.LENGTH_SHORT).show();
        } else {
            mpacierto.start();
            Toast.makeText(this, "Has ganado", Toast.LENGTH_SHORT).show();
        }

    }
}

