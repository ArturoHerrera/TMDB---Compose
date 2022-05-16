# TMDB---Compose
Aplicación con Jetpack Compose, MVVM e integración de la API de TMDB.

ToDo:
Realizado:
✓ Se establece patrón arquitectónico; MVVM.

✓ Inyección de dependencias con Hilt.

✓ UI con Jetpack Compose.

✓ Compose Navigation.

✓ Implementación de la API de Flows de Kotlin para promover la reactividad de la aplicación.

✓ Implementacion de Room para persistencia de datos. Por cuestion de contextos, se decidió hacer tablas separadas.

✓ Se integra la API de TMDB.

✓ Se muestra el detalle de las pel{iculas. (Lo mismo sería para tvShows; las entidades y modelos fueron creados).

Por terminar:
- Implementar las llamadas a los servicios de TvShows. (Es replicar lo hecho con los servicios de Movies)
- Corregir player de video. Investigar como implementar mejor Exoplayer o alguna herramienta similar. 
  Aqui tenemos varias opciones, lo primero es acceder por la URI a nuestro archivo de video, al cual a su vez accedemos por la URL.
  Leyendo la API no encontre como acceder a dichas URL's, por ello deje hardcoded una liga a un mp4. Todas las películas
  que consulte, traían el atributo 'video' asignado en false. Y el endpoint de videos tampoco retornaba alguna URL.
- Aprovechar el paginador que traen los servicios. Falta detectar cuando estoy al final de un scroll o cuando el ultimo indexed item
  de una lista es visible, ese seria mi triger para hacer la peticion con la nueva página.


Se intenta seguir los principios de la arquitectura recomendada por Android.
https://developer.android.com/topic/architecture#recommended-app-arch
https://developer.android.com/kotlin/flow?hl=es-419
