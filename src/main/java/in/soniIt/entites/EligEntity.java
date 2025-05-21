package in.soniIt.entites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="ELIG_DTLS")
public class EligEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer edgTraceId;

    private  String planStatus;
    private Double benifitAmt;
}
