<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Control clientes</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <ul>
            <c:forEach var="cliente" items="${clientes}">
                <li>${cliente.id_cliente} ${cliente.nombre} ${cliente.apellido} ${cliente.email} ${cliente.telefono}</li>
            </c:forEach>
        </ul>
    </body>
</html>
