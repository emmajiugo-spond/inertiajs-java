package io.inertia.core;

import java.util.function.Supplier;

public final class InertiaConfig {

    private final Supplier<String> versionSupplier;
    private final TemplateResolver templateResolver;
    private final JsonSerializer jsonSerializer;

    private InertiaConfig(Builder builder) {
        this.versionSupplier = builder.versionSupplier;
        this.templateResolver = builder.templateResolver;
        this.jsonSerializer = builder.jsonSerializer != null
                ? builder.jsonSerializer
                : new JacksonJsonSerializer();
    }

    public String getVersion() { return versionSupplier.get(); }
    public TemplateResolver getTemplateResolver() { return templateResolver; }
    public JsonSerializer getJsonSerializer() { return jsonSerializer; }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Supplier<String> versionSupplier = () -> "1";
        private TemplateResolver templateResolver;
        private JsonSerializer jsonSerializer;

        private Builder() {}

        public Builder version(String version) {
            this.versionSupplier = () -> version;
            return this;
        }

        public Builder versionSupplier(Supplier<String> versionSupplier) {
            this.versionSupplier = versionSupplier;
            return this;
        }

        public Builder templateResolver(TemplateResolver templateResolver) {
            this.templateResolver = templateResolver;
            return this;
        }

        public Builder jsonSerializer(JsonSerializer jsonSerializer) {
            this.jsonSerializer = jsonSerializer;
            return this;
        }

        public InertiaConfig build() {
            if (templateResolver == null) {
                throw new IllegalStateException("templateResolver is required");
            }
            return new InertiaConfig(this);
        }
    }
}
