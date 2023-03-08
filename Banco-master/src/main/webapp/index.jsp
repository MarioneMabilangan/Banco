<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Banco DAM2</title>
    <style>
        h1 {
            font-weight: bold;
        }
        label {
            font-weight: bold;
        }
        form {
            border: 2px solid grey;
            padding: 20px;
        }
        label, input {
            border: 1px solid grey;
            padding: 5px;
            margin: 5px;
        }
    </style>
</head>
<body>
<h1>Banco DAM2</h1>
<form id="form" action="${pageContext.request.contextPath}/create" method="post">
    <label for="nombre_cliente">Nombre Cliente:</label>
    <input type="text" id="nombre_cliente" name="nombre_cliente"><br>

    <label for="id_fiscal">Id Fiscal (DNI, NIE):</label>
    <input type="text" id="id_fiscal" name="id_fiscal"><br>

    <label for="email_cliente">Email cliente:</label>
    <input type="email" id="email_cliente" name="email_cliente"><br>

    <label for="pais">País:</label>
    <input type="text" id="pais" name="pais"><br>

    <label for="cuenta">Cuenta:</label>
    <input type="text" id="cuenta" name="cuenta"><br>

    <label for="ingreso_inicial">Ingreso inicial (€):</label>
    <input type="number" id="ingreso_inicial" name="ingreso_inicial"><br>

    <button type="submit" name="submit" value="crear_cliente">Crear Cliente</button>
    <button type="submit" name="submit" value="mostrar_cliente">Mostrar Cliente</button>
</form>
</body>
</html>