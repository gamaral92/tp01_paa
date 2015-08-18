/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author gabrielamaral
 */
public class Problema {

    private int quantidadePessoas;
    private int quantidadeGrupos;
    private int[][] matriz;
    private int[][] grupos;
    private int grupoAletorio1;
    private int grupoAletorio2;
    private int pessoaAleatoria1;
    private int pessoaAleatoria2;

    public boolean readFile(String nomeArquivo) {
        try {
            FileReader fileReader = new FileReader(new File(nomeArquivo));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            if (bufferedReader.ready()) {
                String linha = bufferedReader.readLine();
                String[] parametros = linha.split("\t");
                quantidadePessoas = Integer.parseInt(parametros[1]);
            }
            if (bufferedReader.ready()) {
                String linha = bufferedReader.readLine();
                String[] parametros = linha.split("\t");
                quantidadeGrupos = Integer.parseInt(parametros[1]);
            }
            grupos = new int[quantidadeGrupos][];
            int i = 0;
            while (bufferedReader.ready() && i < quantidadeGrupos) {
                String linha = bufferedReader.readLine();
                String[] parametros = linha.split("\t");
                int coluna = Integer.parseInt(parametros[1]);
                grupos[i++] = new int[coluna];
            }
            matriz = new int[quantidadePessoas][quantidadePessoas];
            i = 0;
            while (bufferedReader.ready()) {
                String linha = bufferedReader.readLine();
                String[] parametros = linha.split("\t");
                for (int j = 0; j < parametros.length; j++) {
                    matriz[i][j] = Integer.parseInt(parametros[j]);
                }
                i++;
            }
            bufferedReader.close();
            fileReader.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Arquivo " + nomeArquivo + " não encontrado.");
            return false;
        }
    }

    public void hillClimbing() {
        solucaoInicial();
        int cont = 0;
        while (cont < 50000) {            
            int valor = funcaoObjetivo();
            swap();
            int novoValor = funcaoObjetivo();
            if (novoValor < valor) {
                volta();
            }
            cont++;
        }
        System.out.println(cont + "\t" + funcaoObjetivo());
    }

    private void solucaoInicial() {
        int pessoa = 0;
        for (int i = 0; i < grupos.length; i++) {
            for (int j = 0; j < grupos[i].length; j++) {
                grupos[i][j] = pessoa++;
            }
        }
    }

    private int funcaoObjetivo() {
        int soma = 0;
        for (int[] grupo : grupos) {
            for (int j = 0; j < grupo.length; j++) {
                for (int k = 0; k < grupo.length; k++) {
                    if (j != k) {
                        int pessoa1 = grupo[j];
                        int pessoa2 = grupo[k];
                        soma += matriz[pessoa1][pessoa2];
                    }
                }
            }
        }
        return soma;
    }

    private void swap() {
        Random random = new Random();
        grupoAletorio1 = random.nextInt(quantidadeGrupos);
        grupoAletorio2 = random.nextInt(quantidadeGrupos);
        while (grupoAletorio1 == grupoAletorio2) {
            grupoAletorio2 = random.nextInt(quantidadeGrupos);
        }
        pessoaAleatoria1 = random.nextInt(grupos[grupoAletorio1].length);
        pessoaAleatoria2 = random.nextInt(grupos[grupoAletorio2].length);

        volta();
    }

    private void volta() {
        int aux = grupos[grupoAletorio1][pessoaAleatoria1];
        grupos[grupoAletorio1][pessoaAleatoria1] = grupos[grupoAletorio2][pessoaAleatoria2];
        grupos[grupoAletorio2][pessoaAleatoria2] = aux;
    }

}
