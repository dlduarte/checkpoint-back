package br.com.dld.checkpoint.dto.auth;

/**
 *
 * @author David Duarte
 */
public class RecoveryResponseDto {

    private final String addressee;

    public RecoveryResponseDto(String addressee) {
        this.addressee = addressee;
    }

    public String getAddressee() {
        return addressee;
    }
}
