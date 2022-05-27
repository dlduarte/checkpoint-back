package br.com.dld.checkpoint.configs.validacao;

/**
 *
 * @author David Duarte
 */
public class ErroValidacaoDto {
    private final String campo;
    private final String erro;

    public ErroValidacaoDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
