#language: es

Característica: Prepago
  Validar flujo prepago

  @Prepago
  Esquema del escenario: Escenario: realizar proceso ecommers prepago
    Dado Que Se da inicio Flujo Prepago
    Y Se realiza busqueda de equipo <RowsNumber>.
    Y Seleccion compra <RowsNumber>.
    Entonces Confirmar como invitado en carrito de compra
    Cuando Se realiza el ingreso de informacion <RowsNumber>.
    Y Se debe confirmar datos de envio y facturacion
    Y Se realiza pago con tarjeta de debito <RowsNumber>.
    Y Se confirma solicitud de equipo <RowsNumber>.

    Ejemplos:
     |RowsNumber|
    #|     1    |  
    #|     2    |
    #|     3    |  

              