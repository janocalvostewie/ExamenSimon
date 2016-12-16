package com.example.janillo.simonbis;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.widget.Button;

/**
 * Created by janillo on 30/10/2016.
 */

public class Parpadeo  {


    public static Button miBotonBis;
    public static int tiempoParpadeo=1500;



    public static void parpadear(Button boton){
        //Puesto que vamos a llamar a esta variable desde un Runnable necesitamos trampear el sistema
        //Crear una variable interna para depsués llamarla
        miBotonBis=boton;
        //Establecemos el foco en el botón deseado, lo que hará que cambie de color por la configuración de drawable
        miBotonBis.setFocusable(true);
        miBotonBis.setFocusableInTouchMode(true);
        miBotonBis.requestFocus();
        miBotonBis.postDelayed(new Runnable() {

            @Override
            public void run() {
                //Al cabo de un rato quitamos el foco
                miBotonBis.setFocusable(false);
                miBotonBis.setFocusableInTouchMode(false);
            }
        }, tiempoParpadeo);

    }

}
