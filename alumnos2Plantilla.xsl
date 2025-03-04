<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <html>
      <head>
        <title>LISTADO DE CALIFICACIONES</title>
      </head>
      <body>
        <h1>LISTA DE ALUMNOS</h1>
        <table border="1">
          <tr>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Primer Examen</th>
            <th>Segundo Examen</th>
            <th>Media</th>
          </tr>
          <xsl:apply-templates select="alumnos/alumno"/>
        </table>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="alumno">
    <tr>
      <td>
        <xsl:value-of select="nombre"/>
      </td>
      <td>
        <xsl:value-of select="apellido"/>
      </td>
      <td>
        <xsl:value-of select="calificaciones/primer_examen"/>
      </td>
      <td>
        <xsl:value-of select="calificaciones/segundo_examen"/>
      </td>
      <td>
        <xsl:value-of select="media"/>
      </td>
    </tr>
  </xsl:template>

</xsl:stylesheet>
