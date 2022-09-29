# 4Dogs
<img src="https://github.com/angelcast2002/APP-Proyecto1-PlataformasMoviles/blob/main/APPLogo.png">
 
## Servicios
- [API razas de perros](https://api-ninjas.com/api/dogs)

    - API para algo
  
  ***
- [API inicio de sesión](noche)
  
    - API para algo
  
  ***
## Librerías

- [Rrtrofit](https://square.github.io/retrofit/)
  
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
