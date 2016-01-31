<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:types="http://types.com/Device"
                exclude-result-prefixes="xs"
                version="3.0">
    <xsl:template match="/">
        <html>
            <body>
                <table>
                    <tr>
                        <td>id</td>
                        <td>name</td>
                        <td>origin</td>
                        <td>price</td>
                        <td>isPeripheral</td>
                        <td>consumption</td>
                        <td>cooler</td>
                        <td>deviceGroup</td>
                        <td>COM</td>
                        <td>LPT</td>
                        <td>USB</td>
                    </tr>

                    <xsl:for-each select="devices/device">
                        <tr>
                            <td>
                                <xsl:value-of select="@id"/>
                            </td>
                            <td>
                                <xsl:value-of select="name"/>
                            </td>
                            <td>
                                <xsl:value-of select="origin"/>
                            </td>
                            <td>
                                <xsl:value-of select="price"/>
                            </td>
                            <td>
                                <xsl:value-of select="type/isPeripheral"/>
                            </td>
                            <td>
                                <xsl:value-of select="type/consumption"/>
                            </td>
                            <td>
                                <xsl:value-of select="type/cooler"/>
                            </td>
                            <td>
                                <xsl:value-of select="type/deviceGroup"/>
                            </td>
                            <td>
                                <xsl:value-of select="type/ports/types:COM"/>
                            </td>
                            <td>
                                <xsl:value-of select="type/ports/types:LPT"/>
                            </td>
                            <td>
                                <xsl:value-of select="type/ports/types:USB"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>