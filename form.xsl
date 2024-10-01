<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns="http://sipvs.com/online-notarization"
                exclude-result-prefixes="ns">

    <xsl:output method="html" encoding="UTF-8" indent="yes" />

    <xsl:template match="/ns:notarization-form">
        <html>
            <head>
                <title>Notarization Form Details</title>
                <style>
                    body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f4f4;
                    margin: 0;
                    padding: 20px;
                    }

                    .form-container {
                    max-width: 800px;
                    background-color: #fff;
                    padding: 20px;
                    margin: auto;
                    border-radius: 8px;
                    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                    }

                    h1 {
                    text-align: center;
                    color: #333;
                    }

                    .form-section {
                    margin-bottom: 20px;
                    }

                    .form-section h2 {
                    margin-bottom: 10px;
                    color: #0056b3;
                    font-size: 20px;
                    }

                    label {
                    font-weight: bold;
                    display: block;
                    margin-bottom: 5px;
                    }

                    p {
                    margin: 5px 0;
                    }

                    .contracts-section {
                    border: 1px solid #ccc;
                    padding: 10px;
                    border-radius: 8px;
                    background-color: #f9f9f9;
                    }

                    .contract {
                    margin-bottom: 15px;
                    padding: 10px;
                    border-bottom: 1px solid #ddd;
                    }

                    .contract:last-child {
                    border-bottom: none;
                    }
                </style>
            </head>
            <body>
                <div class="form-container">
                    <h1>Notarization Form Details</h1>

                    <div class="form-section">
                        <h2>Applicant Information</h2>
                        <p><strong>Name:</strong> <xsl:value-of select="ns:applicant/ns:name" /></p>
                        <p><strong>Email:</strong> <xsl:value-of select="ns:applicant/ns:email" /></p>
                    </div>

                    <div class="form-section contracts-section">
                        <h2>Contracts</h2>
                        <xsl:for-each select="ns:contracts/ns:contract">
                            <div class="contract">
                                <p><strong>Contract Title:</strong> <xsl:value-of select="ns:contract-title" /></p>
                                <p><strong>Contract Type:</strong> <xsl:value-of select="ns:contract-type" /></p>
                            </div>
                        </xsl:for-each>
                    </div>

                    <div class="form-section">
                        <h2>Notary Public Information</h2>
                        <p><strong>Name:</strong> <xsl:value-of select="ns:notary-public/ns:name" /></p>
                        <p><strong>Email:</strong> <xsl:value-of select="ns:notary-public/ns:email" /></p>
                    </div>

                    <div class="form-section">
                        <h2>Notarization Date</h2>
                        <p><strong>Date:</strong> <xsl:value-of select="ns:notarization-date" /></p>
                    </div>

                    <div class="form-section">
                        <h2>Contract Agreement Document</h2>
                        <p><strong>Document:</strong> <xsl:value-of select="ns:contract-agreement-document" /></p>
                    </div>

                    <div class="form-section">
                        <h2>Receive Confirmation Email</h2>
                        <p>
                            <strong>Would you like to receive a confirmation email?</strong>
                            <xsl:choose>
                                <xsl:when test="ns:receive-confirmation-email = 'true'">Yes</xsl:when>
                                <xsl:otherwise>No</xsl:otherwise>
                            </xsl:choose>
                        </p>
                    </div>

                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
