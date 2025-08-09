package de.smartgrow.stack;

import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.*;

public class LocalStack extends Stack {
    private final Vpc vpc;

    public LocalStack(final App scope, final String id, final StackProps props){
        super(scope, id, props);
        this.vpc = createVpc();
    }

    private Vpc createVpc() {
        return Vpc.Builder
                .create(this, "GreenhouseManagementVPC")
                .vpcName("GreenhouseManagementVPC")
                .maxAzs(2)
                .build();

    }

    public static void main(final String[] args){
        App app = new App(AppProps.builder().outdir("./cdk.out").build());

        StackProps props = StackProps.builder()
                .synthesizer(new BootstraplessSynthesizer())
                .build();

        new LocalStack(app, "LocalStack", props);

        app.synth();

        System.out.println("App synthesizing in progress...");
    }
}
