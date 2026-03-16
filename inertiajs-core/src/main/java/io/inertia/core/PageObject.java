package io.inertia.core;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class PageObject {

    private final String component;
    private final Map<String, Object> props;
    private final String url;
    private final String version;
    private final Map<String, List<String>> deferredProps;
    private final List<String> mergeProps;
    private final List<String> prependProps;
    private final List<String> deepMergeProps;
    private final Map<String, String> matchPropsOn;

    private PageObject(Builder builder) {
        this.component = builder.component;
        this.props = builder.props;
        this.url = builder.url;
        this.version = builder.version;
        this.deferredProps = nullIfEmpty(builder.deferredProps);
        this.mergeProps = nullIfEmpty(builder.mergeProps);
        this.prependProps = nullIfEmpty(builder.prependProps);
        this.deepMergeProps = nullIfEmpty(builder.deepMergeProps);
        this.matchPropsOn = nullIfEmpty(builder.matchPropsOn);
    }

    public String getComponent() { return component; }
    public Map<String, Object> getProps() { return props; }
    public String getUrl() { return url; }
    public String getVersion() { return version; }
    public Map<String, List<String>> getDeferredProps() { return deferredProps; }
    public List<String> getMergeProps() { return mergeProps; }
    public List<String> getPrependProps() { return prependProps; }
    public List<String> getDeepMergeProps() { return deepMergeProps; }
    public Map<String, String> getMatchPropsOn() { return matchPropsOn; }

    public static Builder builder() { return new Builder(); }

    private static <T> List<T> nullIfEmpty(List<T> list) {
        return list != null && !list.isEmpty() ? list : null;
    }

    private static <K, V> Map<K, V> nullIfEmpty(Map<K, V> map) {
        return map != null && !map.isEmpty() ? map : null;
    }

    public static final class Builder {
        private String component;
        private Map<String, Object> props;
        private String url;
        private String version;
        private Map<String, List<String>> deferredProps;
        private List<String> mergeProps;
        private List<String> prependProps;
        private List<String> deepMergeProps;
        private Map<String, String> matchPropsOn;

        private Builder() {}

        public Builder component(String component) { this.component = component; return this; }
        public Builder props(Map<String, Object> props) { this.props = props; return this; }
        public Builder url(String url) { this.url = url; return this; }
        public Builder version(String version) { this.version = version; return this; }
        public Builder deferredProps(Map<String, List<String>> deferredProps) { this.deferredProps = deferredProps; return this; }
        public Builder mergeProps(List<String> mergeProps) { this.mergeProps = mergeProps; return this; }
        public Builder prependProps(List<String> prependProps) { this.prependProps = prependProps; return this; }
        public Builder deepMergeProps(List<String> deepMergeProps) { this.deepMergeProps = deepMergeProps; return this; }
        public Builder matchPropsOn(Map<String, String> matchPropsOn) { this.matchPropsOn = matchPropsOn; return this; }

        public PageObject build() { return new PageObject(this); }
    }
}
