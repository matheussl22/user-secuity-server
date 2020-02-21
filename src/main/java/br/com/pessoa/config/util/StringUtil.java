package br.com.pessoa.config.util;

import org.apache.commons.lang.WordUtils;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.Objects;

public class StringUtil {

    private StringUtil() {
    }

    public static String formatarCPF(String cpf) {
        String cpfSemFormatacao = removeFormatacaoEZeroEsquerda(cpf);
        String cpfFormatado = cpfSemFormatacao;

        if (possuiValor(cpfSemFormatacao)) {
            cpfSemFormatacao = preencheComZeros(cpfSemFormatacao, 11);
            cpfFormatado = cpf.substring(0, 3).concat(".");
            cpfFormatado = cpfFormatado.concat(cpfSemFormatacao.substring(3, 6)).concat(".");
            cpfFormatado = cpfFormatado.concat(cpfSemFormatacao.substring(6, 9)).concat("-");
            cpfFormatado = cpfFormatado.concat(cpfSemFormatacao.substring(9, 11));
        }
        return cpfFormatado;
    }

    public static String removeFormatacaoEZeroEsquerda(String texto) {
        if (texto == null) {
            return null;
        }
        String semFormatacao = removeFormatacao(texto);
        semFormatacao = semFormatacao.replaceFirst("0*", "");
        return semFormatacao;
    }

    public static String removeFormatacao(String texto) {
        return texto.replaceAll("[^\\p{Alpha}\\p{Digit}]+", "");
    }

    public static String preencheComZeros(String texto, Integer tamanho) {
        return preencheComCaracterEsquerda(texto, tamanho, "0");
    }

    public static String preencheComCaracterEsquerda(String texto, Integer tamanho, String caracter) {

        String textoNaoFormatado = texto;

        while (textoNaoFormatado.length() < tamanho) {
            textoNaoFormatado = caracter.concat(textoNaoFormatado);
        }
        return textoNaoFormatado;

    }

    public static boolean possuiValor(String valor) {
        if (valor == null) {
            return false;
        }

        String novoValor = valor.trim();
        return (!novoValor.isEmpty());
    }

}
