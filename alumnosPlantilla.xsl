<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html><xsl:apply-templates /></html>
    </xsl:template>
    <xsl:template match="listadealumnos">
    <head><title>LISTADO DE ALUMNOS</title></head>
    <body>
        <h1>LISTA DE ALUMNOS</h1>
        <table border="1">
            <tr><th>Nombre</th><th>Edad</th></tr>
            <xsl:apply-templates select="alumno" />
        </table>
    </body>
    </xsl:template>

    <xsl:template match="alumno">
        <tr><xsl:apply-templates /></tr>
    </xsl:template>

    <xsl:template match="nombre|edad">
        <tr><xsl:apply-templates /></tr>
    </xsl:template>
    
</xsl:stylesheet>