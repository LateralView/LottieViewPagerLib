# LottieViewPagerLib

- Agregar la librería
- Agregar en gradle compatibilidad con Java 1.8
compileOptions {
        targetCompatibility 1.8
        sourceCompatibility 1.8
    }
    
    Asegurarse que soportan la misma versión de API
- Agregar en el layout el vínculo con el componente Lottie Pager 
- Agregar en la carpeta de assets la animación
-- Si el proyecto no tiene la carpeta assets agregarla con el constructor del IDE.
- Agregar en el XML o en la actividad el set up view
-- Por ejemplo:
  <lv.joaquin.lottieviewpagercomponent.LottiePager
      android:id="@+id/lottie_pager"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      app:lottieFilePath="animations/animation_test.json"
      app:lottieAnimationLength="4010">
- Agregar en la Actividad los steps
-- Ejemplo:
        LottiePager lottiePager = findViewById(R.id.lottie_pager);
        lottiePager.setUpView(new int[]{0,1000,2000,3000,4000});
- Correr app
