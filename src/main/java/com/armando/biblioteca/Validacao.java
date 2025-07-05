package com.armando.biblioteca;



import java.io.*;

import java.util.StringTokenizer;



public class Validacao {



    private static  BufferedReader entry = new BufferedReader(new InputStreamReader(System.in));



    //validar dados inteiros

    public int validarInt(String msg, int max, int min) {

        int valor = 0;



        try {

            do {

                System.out.print(msg);

                valor = Integer.parseInt(entry.readLine());



                if (valor < min || valor > max) {

                    System.out.println("Entrada fora dos parametros\nTente novamnte");

                }

            } while (valor < min || valor > max);

        } catch (NumberFormatException e) {

            System.out.println("Por favor, Insira numeros apenas;");

            return validarInt(msg, max, min);

        } catch (IOException t) {

            System.out.println("Entrada Invalida\nTente novamente");

            return validarInt(msg, max, min);

        }



        return valor;

    }



    //Validar NumTelefone

    public boolean validarTelefone(String telefone) {

        

        try {

            Integer.parseInt(telefone);

            if((!telefone.substring(0, 2).equalsIgnoreCase("82") &&

            !telefone.substring(0, 2).equalsIgnoreCase("83") &&

            !telefone.substring(0, 2).equalsIgnoreCase("84") &&

            !telefone.substring(0, 2).equalsIgnoreCase("85") &&

            !telefone.substring(0, 2).equalsIgnoreCase("86") &&

            !telefone.substring(0, 2).equalsIgnoreCase("87")) || telefone.length() != 9) {

                System.out.println("Telefone Invalido\nO mesmo deve conter 9 digitos, dos quais 2 referenciam a operadora(82/3, 84/5 e 86/7)\nTente novamente;");

                return false;

            }

        } catch (NumberFormatException t) {

            System.out.println("Ensira apenas numeros\nTente novamente;");

            return false;

        }

        return true;

    }



    //validar dados String

    public String validarString(String msg) {

        String dado = "";



        try {

            do {

                System.out.print(msg);

                dado = entry.readLine();

                if (dado.length() < 3) {

                    System.out.println("Entrada invalida");

                }

            } while (dado.length() < 3);

            for (char c: dado.toCharArray()) {

                if (Character.isDigit(c)) {

                    System.out.println("Entrada invalida\nTente novamente");

                    return validarString(msg);

                }

            }

            StringTokenizer tok = new StringTokenizer(dado, " ");

            String aux = tok.nextToken(),palavra = aux.toUpperCase().charAt(0) + aux.substring(1).toLowerCase();

            while(tok.hasMoreTokens()) {

                aux = tok.nextToken();

                palavra = palavra + " " + aux.toUpperCase().charAt(0) + aux.substring(1).toLowerCase();

            }

            dado = palavra;

            

        } catch (IOException e) {

            System.out.println("Erro\nTente novamente: ");

            return validarString(msg);

        }

        return dado;

    }



    //Validar nome

    public boolean validarNome(String nome) {



        try {

            if (nome.length() < 3) {

                System.out.println("Entrada invalida");

                return false;

            }

            for (char c: nome.toCharArray()) {

                if (Character.isDigit(c)) {

                    System.out.println("Entrada invalida\nTente novamente");

                    return false;

                }

            }

            StringTokenizer tok = new StringTokenizer(nome, " ");

            String aux = tok.nextToken(),palavra = aux.toUpperCase().charAt(0) + aux.substring(1).toLowerCase();

            while(tok.hasMoreTokens()) {

                aux = tok.nextToken();

                palavra = palavra + " " + aux.toUpperCase().charAt(0) + aux.substring(1).toLowerCase();

            }

            nome = palavra;

            

        } catch (Exception e) {

            System.out.println("Erro\nTente novamente: ");

            return false;

        }

        return true;

    }



    // Metodos validarID

    //ID's devem estar na forma EST#####, DOC#####, FUN#####, LIV#####, AUT#####, EMP######, EXE#####, ARE#####, EDI#####)

    public String validarID(String lastId) {

        int id = 0;

        try {

            id = Integer.parseInt(lastId.substring(3));

        } catch (NumberFormatException e) {

            System.out.println("NumberFormatException");

            return validarID(lastId);

        }

        id++;

        if(id < 10) {

            return lastId.substring(0, 3) + "0000" + Integer.toString(id);

        } else if(id < 100){

            return lastId.substring(0, 3) + "000" + Integer.toString(id);

        } else if (id < 1000) {

           return lastId.substring(0, 3) + "00" + Integer.toString(id);

        } else if(id < 10000) {

            return lastId.substring(0, 3) + "0" + Integer.toString(id);

        } else{ 

            return lastId.substring(0, 3) + Integer.toString(id);



        }



    }



    /*public String validarID(int indice) {

        if(indice == 1) {

            return "EST00001";

        } else if(indice == 2) {

            return "DOC00001";

        } else if(indice == 3) {

            return "FUN00001";

        } else if(indice == 4) {

            return "LIV00001";

        } else if(indice == 5){

            return "EXE00001";

        } else if(indice == 6) {

            return "ARE00001";

        } else if(indice == 7) {

            return "AUT00001";

        } else if(indice == 8) {

            return "EDI00001";

        } else {

            return "EMP00001";

        }

    }*/



    /*public String validarID(String msg, String tipo) {

        System.out.print(msg);

        String id = "";

        try {

             id = entry.readLine().toUpperCase();

             if(id.length() != 8) {

                System.out.println("O ID deve conter 8 caracteres\nTente novamente");

                return validarID(msg, tipo);

            }

             Integer.parseInt(id.substring(3));

            if(tipo.equals("USU")) {

                if(!id.substring(0, 3).equals("EST") && !id.substring(0, 3).equals("DOC") && !id.substring(0, 3).equals("FUN")) {

                    System.out.println("ID Invalido\nO mesmo deve iniciasr por 'EST', 'DOC' ou 'FUN'\nTente Novamente");   

                    return validarID(msg, tipo);

                }

            } else {

                if(!id.substring(0, 3).equals(tipo)) {

                    System.out.println("Invalido\nO ID deve iniciar por " + tipo + "\nTente novamente");

                    return validarID(msg, tipo);

                }

            }



            if(id.substring(3).length() != 5) {

                System.out.println("O ID deve conter 5 digitos\nTente novamente");

            }

        } catch (NumberFormatException | IOException e) {

            System.out.println("Formato Errado\n Tente novamente");

            return validarID(msg, tipo);

        }

        return id;



    }*/

    



                                                                                               }
