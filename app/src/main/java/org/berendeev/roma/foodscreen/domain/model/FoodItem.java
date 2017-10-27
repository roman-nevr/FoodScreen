package org.berendeev.roma.foodscreen.domain.model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class FoodItem {

    public abstract String name();
    public abstract String url();

    public abstract Builder toBuilder();

    public static FoodItem create(String name, String url) {
        return builder()
                .name(name)
                .url(url)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_FoodItem.Builder();
    }


    @AutoValue.Builder public abstract static class Builder {
        public abstract Builder name(String name);
        public abstract Builder url(String url);

        public abstract FoodItem build();
    }
}
