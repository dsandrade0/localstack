package info.dsandrade.info.LocalStack.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;

@Profile("local")
@Configuration
public class AwsConfiguration {

    @Value("${spring.cloud.aws.endpoint}")
    private String endpointUrl;
    private static final Region DEFAULT_REGION = Region.US_EAST_1;


    @Bean
    public SqsClient sqsAsyncClient() {
        return SqsClient.builder()
                .region(DEFAULT_REGION)
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .applyMutation(builder -> {
                    builder.endpointOverride(URI.create(endpointUrl));
                })
                .build();
    }
}
