package kg.manas.crm.configs;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReflectionsConfig {

    @Bean
    Reflections reflections() {
        return new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("kg.manas.crm.converters"))
                .setScanners(Scanners.values()));
    }
}
