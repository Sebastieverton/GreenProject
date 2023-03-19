package PizzaEuGostum.ufpb.br;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SistemaAmigo {

    public PrintStream fora;
    private List<Mensagem> mensagem;

    private List<Amigo> amigos;


    public SistemaAmigo (){
        this.mensagem =new ArrayList<>();
        this.amigos = new ArrayList<>();

    }

    public SistemaAmigo(ArrayList<Mensagem> mensagem, ArrayList<Amigo> amigo) {
        this.mensagem = mensagem;
        this.amigos = amigo;
    }

    public  void cadastraAmigo (String nome ,String emailAmigo ) throws JaFoiCadastradoException{
        Amigo novoAmigo = new Amigo(nome,emailAmigo);
        if (!this.amigos.contains(novoAmigo)){
            this.amigos.add(novoAmigo);
        }else {
            throw new JaFoiCadastradoException("Essa pessoa ja foi cadstrada");
        }
    }
    public Amigo pequisaAmigo (String emailAmigo) throws EmailNaoEncontradoException {
        for(Amigo a: amigos ){
            if(a.getEmail().equals(emailAmigo)){
                return a;
            }
        }
        throw new EmailNaoEncontradoException("Esse email não foi encontrado");

    }

    public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean anonima) throws FoiEnviadoExcption{
        MensagemParaTodos enviar = new MensagemParaTodos(texto,emailRemetente,anonima);
        if(!this.mensagem.contains(enviar)){
            this.mensagem.add(enviar);
        }else {
            throw new FoiEnviadoExcption("Essa Mensagem ja foi enviada");
        }

    }

    public void enviarMensagemParaAlguem (String texto, String emailRemetente, boolean anonima, String emailDestinatario)throws FoiEnviadoExcption{
        MensagemParaAlguem enviar = new MensagemParaAlguem(texto, emailRemetente, anonima, emailDestinatario);
        if (!this.mensagem.contains(enviar)){
            this.mensagem.add(enviar);
        }
        else {
            throw new FoiEnviadoExcption("essa mensagem ja foi enviada");
        }

    }

    public List<Mensagem> pequisaMensagemAnonimas () {
        List<Mensagem> mensagensAnonima = new ArrayList<>();
        for(Mensagem m: this.mensagem){
            if (m.ehAnonima()){
                mensagensAnonima.add(m);
            }
        }return mensagensAnonima;
    }

    public List<Mensagem> pesquisarTodasAsMensagen() {
        List<Mensagem> mensagensCopia = new LinkedList<>(this.mensagem);
        return mensagensCopia;
    }
    public  void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoDaPessoa) throws AmigoInexistenteException {
        for (Amigo amigo : this.amigos){
            if(amigo.getEmail().equals(emailDaPessoa)){
                amigo.setEmailSorteado(emailAmigoDaPessoa);
                return;
            }
        }throw new AmigoInexistenteException("esse amigo não existe");

    }


    public String pesquisarAmigoSecretoDaPessoa(String emailDaPessoa)throws AmigoInexistenteException, AmigoNaoSorteadoException {
        for (Amigo amigo : this.amigos){
            if (amigo.getEmail().equals(emailDaPessoa)){
                if(amigo.getEmailSorteado() == null){
                    throw new AmigoNaoSorteadoException("O email" + amigo.getEmailSorteado() + "é" + null);
                } else {
                    return amigo.getEmailSorteado();
                }
            }
        }
        throw new AmigoInexistenteException("esse amigo não existe");
    }


    public void pesquisaMensagensAnônimas() {
    }

    public PrintStream getFora() {
        return fora;
    }

    public void setFora(PrintStream fora) {
        this.fora = fora;
    }

    public String pesquisaAmigoSecretoDe(String s) {
        return s;
    }
}
