package br.com.api.estacionamento.Entidades.Estrutura;

import br.com.api.estacionamento.Entidades.Fabrica.Carro;

//possibilidade do Estacionamento ter um piso ou mais

public class PisoEstacionamento {

    //Nome do Piso do Estacionamento (Usado para identificação (E1, E2 ...G1, G2)
    private String nome;

    //É o Array com todas as vagas do Piso do Estacionamento
   private  Vaga[] vagas;

    public String getNome() {
        return nome;
    }

    public Vaga[] getVagas() {
        return vagas;
    }

    //retorna número de vagas total do estacionamento
   public int tamanhoEstacionamento(){
       return vagas.length;
   }

   //Inicializa o piso do estacionamento, setando nome e criando todas as vagas vazias
   public PisoEstacionamento(String nome,int numVagas) {
       this.nome = nome;
       vagas = new Vaga[numVagas];
       for (int i = 0; i < vagas.length; i++) {
           vagas[i] = new Vaga(true, null);
       }
   }

   //Libera todas as vagas
   public void LiberaTodasVagas(){
       for (int i = 0; i < vagas.length; i++) {
           vagas[i].setVazia(true);
           vagas[i].setCarro(null);
       }
   }

   //Seta em uma Vaga um Carro e torna a vaga preenchida (pode lançar exceção em caso de erro)
   public void PreencheVaga(int vaga, Carro carro){
       for (int i = 0; i<vagas.length;i++) {
           if(i == vaga){
               if(vagas[i].isVazia()){
                   vagas[i].setVazia(false);
                   vagas[i].setCarro(carro);
               }else throw new IllegalArgumentException("Vaga já está preenchida!");

           }
       }
   }


    //Seta na Vaga o status de vazio e iguala a null o campo carro (pode lançar exceção em caso de erro)
    public void LiberaVaga(int vaga){
        for (int i = 0; i<vagas.length;i++) {
            if(i == vaga){
                if(!vagas[i].isVazia()){
                    vagas[i].setVazia(true);
                    vagas[i].setCarro(null);
                }else throw new IllegalArgumentException("Vaga já está preenchida!");
            }
        }
    }

    //Conta todas as vagas disponíveis
    public int ContaVagasDisponiveis(){
       int vazias = 0;
        for (Vaga vaga : vagas) {
            if (vaga.isVazia()) vazias += 1;
        }
       return vazias;
    }

    //Verifica se o piso está totalmente ocupado
    public boolean EstaCheio(){
        return ContaVagasDisponiveis() == 0;
    }


}
