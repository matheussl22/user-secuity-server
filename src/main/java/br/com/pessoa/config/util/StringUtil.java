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

    public static String formatarCNPJ(String cnpj) {
        String cnpjSemFormatacao = removeFormatacaoEZeroEsquerda(cnpj);
        String cnpjFormatado = cnpjSemFormatacao;
        if (possuiValor(cnpjSemFormatacao)) {
            cnpjSemFormatacao = preencheComZeros(cnpjSemFormatacao, 14);
            cnpjFormatado = cnpj.substring(0, 2).concat(".");
            cnpjFormatado = cnpjFormatado.concat(cnpjSemFormatacao.substring(2, 5)).concat(".");
            cnpjFormatado = cnpjFormatado.concat(cnpjSemFormatacao.substring(5, 8)).concat("/");
            cnpjFormatado = cnpjFormatado.concat(cnpjSemFormatacao.substring(8, 12)).concat("-");
            cnpjFormatado = cnpjFormatado.concat(cnpjSemFormatacao.substring(12, 14));
        }
        return cnpjFormatado;
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

    public static String preencheComCaracterDireita(String texto, Integer tamanho, String caracter) {

        String textoNaoFormatado = texto;

        while (textoNaoFormatado.length() < tamanho) {
            textoNaoFormatado = textoNaoFormatado.concat(caracter);
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

    public static String removeAcentos(String str) {

        String palavra = str;

        palavra = Normalizer.normalize(palavra, Normalizer.Form.NFD);
        palavra = palavra.replaceAll("[^\\p{ASCII}]", "");
        return palavra;
    }

    public static boolean numeroValido(String valor) {
        String numero = removeFormatacaoEZeroEsquerda(valor);
        if (Objects.nonNull(numero)) {
            return numero.equals(valor) && possuiValor(valor);
        }

        return false;
    }

    public static boolean mesmaString(String valor1, String valor2) {
        String string1 = valor1 == null ? "" : valor1;
        String string2 = valor2 == null ? "" : valor2;
        string1 = removeAcentos(string1);
        string2 = removeAcentos(string2);
        return string1.equalsIgnoreCase(string2);
    }

    public static String trocaAbreviacoesPorPalavra(String frase) {

        if (Objects.isNull(frase)) {
            return null;
        }

        String fraseSemAbreviacao = frase;

        fraseSemAbreviacao = " ".concat(fraseSemAbreviacao.toLowerCase().replaceAll("jd.", "jardim "));
        fraseSemAbreviacao = fraseSemAbreviacao.replaceAll("av\\.", "avenida ");
        fraseSemAbreviacao = fraseSemAbreviacao.replaceAll(" ser ", " servidao ");
        fraseSemAbreviacao = fraseSemAbreviacao.replaceAll(" avn ", " avenida ");
        fraseSemAbreviacao = fraseSemAbreviacao.replaceAll(" rod ", " rodovia ");
        fraseSemAbreviacao = fraseSemAbreviacao.replaceAll(" trv ", " travessa ");
        fraseSemAbreviacao = fraseSemAbreviacao.replaceAll("rua/av.", "");
        fraseSemAbreviacao = fraseSemAbreviacao.replaceAll("\\s+", " ").trim();
        return fraseSemAbreviacao;
    }

    public static String ajustarNomeParaPesquisa(String nome) {

        String nomePesquisa = nome;

        nomePesquisa = trocaAbreviacoesPorPalavra(nomePesquisa);
        nomePesquisa = removeAcentos(nomePesquisa);
        return nomePesquisa.toLowerCase();
    }

    public static String formatarNrCep(String nrCep) {

        String numeroFormatado = nrCep;

        numeroFormatado = removeFormatacaoEZeroEsquerda(numeroFormatado);
        if (possuiValor(numeroFormatado)) {
            numeroFormatado = preencheComZeros(numeroFormatado, 8);
            if (possuiValor(numeroFormatado)) {
                numeroFormatado = numeroFormatado.substring(0, 5) + "-" + numeroFormatado.substring(5);
            }
        }
        return numeroFormatado;
    }

    public static String capitalizar(String texto) {
        if (Objects.isNull(texto)) {
            return null;
        }

        String capitalizado = WordUtils.capitalizeFully(texto);
        for (String palavra : Arrays.asList(" De ", " Da ", " Das ", " Do ", " Dos ", " Na ", " Nas ", " No ", " Nos ",
                " A ", " E ", " O ", " Em ", " Com ")) {
            capitalizado = capitalizado.replaceAll(palavra, palavra.toLowerCase());
        }
        return capitalizado;
    }

    /**
     * Define o primeiro caracter para upper
     *
     * @param string texto a ser modificado
     * @return texto com o primeiro caracter em uppper
     */
    public static String setFirstToUpper(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1, string.length());
    }
}
