package org.berendeev.roma.foodscreen.domain.model;


import com.google.auto.value.AutoValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Android Studio
 *
 * @author Roman Berendeev (roman.berendeev@rentateam.ru)
 */
@AutoValue
public abstract class BonusAction {

	public abstract String title();
	public abstract String imageUrl();

	public long getBonusActionLongId(){
		long result = title().hashCode();
		result = 31 * result + imageUrl().hashCode();
		return result;
	}

	public static BonusAction create(String title, String imageUrl) {
		return builder()
				.title(title)
				.imageUrl(imageUrl)
				.build();
	}

	public static Builder builder() {
		return new AutoValue_BonusAction.Builder();
	}

	@AutoValue.Builder
	public abstract static class Builder {
		public abstract Builder title(String title);

		public abstract Builder imageUrl(String imageUrl);

		public abstract BonusAction build();
	}
}
