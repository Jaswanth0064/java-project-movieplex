package com.movieplex.serviceimpl;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QrCodeService {

    public byte[] generateQRCode(String text, int width, int height) {

        try {

            QRCodeWriter writer = new QRCodeWriter();

            BitMatrix bitMatrix = writer.encode(
                    text,
                    BarcodeFormat.QR_CODE,
                    width,
                    height);

            ByteArrayOutputStream output =
                    new ByteArrayOutputStream();

            MatrixToImageWriter.writeToStream(
                    bitMatrix,
                    "PNG",
                    output);

            return output.toByteArray();

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

}