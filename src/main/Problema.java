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

/**
 *
 * @author gabrielamaral
 */
public class Problema {
    
    private int quantidadePessoas;
    private int quantidadeGrupos;
    private int[][] matriz;
    private int[][] grupos;
    
    public boolean readFile(String nomeArquivo){
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
    
    public void hillClimbing(){
        solucaoInicial();
        int N = 1000;
        for (int i = 0; i < N; i++) {
            int valor = funcaoObjetivo();
            swap();
            int novoValor = funcaoObjetivo();
            if(novoValor < valor){
                volta();
            }
        }
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void swap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void volta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
