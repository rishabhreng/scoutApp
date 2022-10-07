package com.scoutingapp;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;


public class QRFuncs {
    public static BufferedImage generateQRCode(String text, String path) throws Exception {
        QRCodeWriter writer = new QRCodeWriter();
        BufferedImage bufferedImage;
        BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 320, 320);

        Path filePath = Paths.get(path);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", filePath);
        bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        return bufferedImage;
    }
}