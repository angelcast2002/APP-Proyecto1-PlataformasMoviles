# 4Dogs

<p align="center"> <img src="https://github.com/angelcast2002/APP-Proyecto1-PlataformasMoviles/blob/main/APPLogo.png" width = "500"> </p>

## Servicios
- [API Dogs de: API Ninjas](https://api-ninjas.com/api/dogs)

    - API que obtiene información sobre más de 200 razas distintas de perros. Esta API nos permitirá brindarle al usuario recomendaciones sobre su perro dependiendo de la raza de este.
    - Los resultados que se le brinden al usuario serán relacionados al cuidado del perro. Información como: qué tan propensa a ladrar es la raza, qué tan propensa a soltar pelo, qué tanto grooming requiere, etc. Esta información (basada en rankings del 1-5 en el API), nos permitirá recomendarle al usuario tips para cuidar de su mascota y evitar problemas. Recomendaciones para evitar que suelte tanto pelo, que tan seguido llevarlo al grooming, qué tan bien se lleva con otros perros, etc. 
  
  ***
- [API Firebase](https://firebase.google.com/docs/auth/android/google-signin?hl=es-419#kotlin+ktx)
  
    - API que por medio de Google Authentication, nos permitirá llevar el control y manejo de las cuentas de los distintos usuarios.
  
  ***
## Librerías

- [Retrofit](https://square.github.io/retrofit/)
  
    - Librería dedicada al cosumo de APIs de tipo REST, esta librería nos permitirá la inclusión de la información que solicitemos a las APIs enlistadas en la sección de servicios, con el fin de contar con un login funcional de manera independiente al dispositivo, y para poder brindarle al usuario información acerca de su mascota.

    ~~~
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.4'  
    ~~~
  
  ***
  
- [Coil](https://coil-kt.github.io/coil/)
  
    - Librería dedicada al procesamiento de imágenes, tanto locales como de internet, con esto podremos cargar la imágen de la mascota del usuario.


    ~~~
    def coil_version = "2.2.0"
    implementation "io.coil-kt:coil:$coil_version"  
    ~~~
  
  ***
  
- [okhttp3](https://square.github.io/okhttp/)
  
    - Cliente HTTP+SPDY encargada de realizar las peitciones a los servidores donde se aloja la información de las APIs consumidas, mediante la configuración en código de URLs dinámicos.

    ~~~
    implementation 'com.squareup.okhttp3:logging-interceptor:4.9.3' 
    ~~~
  ***
- [Jetpack](https://www.googleadservices.com/pagead/aclk?sa=L&ai=DChcSEwiw1fPFh8P6AhWNwYYKHSkpB2MYABAAGgJ2dQ&ohost=www.google.com&cid=CAESauD2y4p03bSt6aznDs3RrCd-KxCg9m_nrU0KYST0gbZ9ywwt8EHge7t7kDpeknIjW51u002VQhySvNV_qruNJDCATVw8U3DLufaoYOnAbpV21eEWdSD0oRGopdcFTplSmZj_MN7EWzv_o8o&sig=AOD64_0MauLCLbYoCyG05LNrEJyj8p9Dgg&q&adurl&ved=2ahUKEwimx-3Fh8P6AhXHRjABHT84BkAQ0Qx6BAgHEAE)
    - Componente de interfaz de usuario que le ayuda a crear diseños de dos paneles para dispositivos de doble pantalla, plegables y de pantalla grande.
    ~~~
    def nav_version = "2.5.1"
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    ~~~
    
