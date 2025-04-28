package in.soniIt.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "admin")
public class AppProperties {

   private Map<String,String > messages= new HashMap<>();
}
