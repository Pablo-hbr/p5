# P5
Aplicación web que usa Spring JPA para persistir los datos de un API REST de gestión de usuarios.
El API permite el registro de nuevos usuarios y su identificación mediante email y password.
Una vez identificados, se emplea una cookie de sesión para autenticar las peticiones que permiten 
a los usuarios leer, modificar y borrar sus datos. También existe un endpoint para cerrar la sesión.  

## Endpoints

// TODO#1: rellena la tabla siguiente analizando el código del proyecto


| Método | Ruta                         | Descripción                                              | Respuestas                                                       |
|--------|------------------------------|----------------------------------------------------------|------------------------------------------------------------------|
| POST   | `/api/users`                | Registro de un nuevousuario.                             | `201 Created` si va bien, `409 Conflict` si ya existe            |
| POST   | `/api/users/me/session`     | Login del usuario, genera token y crea cookie de sesión. | `201 Created`, o `401 Unauthorized` si falla                     |
| DELETE | `/api/users/me/session`     | Logout del usuario.                                      | `204 No Content`, o `401 Unauthorized` si falla                  |
| GET    | `/api/users/me`             | Devuelve el perfil del usuario autenticado.              | `200 OK`, o `401 Unauthorized` si la sesión es inválida          |
| PUT    | `/api/users/me`             | Actualiza el perfil del usuario autenticado.             | `200 OK`, o `401 Unauthorized` si la sesión es inválida          |
| DELETE | `/api/users/me`             | Elimina la cuenta del usuario autenticado.               |  `204 No Content`, o `401 Unauthorized` si la sesión es inválida |


## Comandos 

- Construcción: 
  ```sh
  ./mvnw clean package
  ```

- Ejecución: 
  ```sh
  ./mvnw spring-boot:run
  ```

- Tests:
  ```sh
  ./mvnw test
  ```
