#language: es

Característica: PostPago
  Validar flujo postPago

  @Postpago
  Esquema del escenario: realizar proceso ecommers prepago
    Dado Que Se da inicio Flujo PostPago
    Y Se ingresa datos para iniciar proceso <RowsNumber>.
    Entonces Se selecciona un plan <RowsNumber>.
    Cuando Se Ingresa numero de serie <RowsNumber>.
    Y Se ingresa datos personales <RowsNumber>.
    Entonces Se confirma solicitud de plan <RowsNumber>.

    Ejemplos:
|RowsNumber|
#|     1    |
#|     2    |
#|     3    |
 |     4    |
