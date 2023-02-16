package alqonquin.cst2335.vill0413;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

	final private int delay = 1000;

	@Rule
	public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
			new ActivityScenarioRule<>(MainActivity.class);

/*	@Test
	public void mainActivityTest() {
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ViewInteraction appCompatEditText = onView(withId(R.id.editText));
		appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());

		ViewInteraction materialButton = onView((withId(R.id.button)));
		materialButton.perform(click());

		ViewInteraction textView = onView((withId(R.id.textView)));
		textView.check(matches(withText("You shall not pass!")));
	}*/

	/**
	 * Test case to verify password complexity when an uppercase is not used
	 */
	@Test
	public void testFindMissingUpperCase(){

		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ViewInteraction appCompatEditText = onView(withId(R.id.editText));
		appCompatEditText.perform(replaceText("pass1234*"));

		ViewInteraction materialButton = onView(withId(R.id.button));
		materialButton.perform(click());

		ViewInteraction textView = onView(withId(R.id.textView));
		textView.check(matches(withText("You shall not pass!")));
	}

	/**
	 * Test case to verify password complexity when a lowercase is not used
	 */
	@Test
	public void testFindMissingLowerCase(){

		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ViewInteraction appCompatEditText = onView(withId(R.id.editText));
		appCompatEditText.perform(replaceText("PASS1234*"));

		ViewInteraction materialButton = onView(withId(R.id.button));
		materialButton.perform(click());

		ViewInteraction textView = onView(withId(R.id.textView));
		textView.check(matches(withText("You shall not pass!")));
	}

	/**
	 * Test case to verify password complexity when a number is not used
	 */
	@Test
	public void testFindMissingNumber(){

		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ViewInteraction appCompatEditText = onView(withId(R.id.editText));
		appCompatEditText.perform(replaceText("Pass*"));

		ViewInteraction materialButton = onView(withId(R.id.button));
		materialButton.perform(click());

		ViewInteraction textView = onView(withId(R.id.textView));
		textView.check(matches(withText("You shall not pass!")));
	}

	/**
	 * Test case to verify password complexity when a special character is not used
	 */
	@Test
	public void testFindMissingSpecialChar(){

		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ViewInteraction appCompatEditText = onView(withId(R.id.editText));
		appCompatEditText.perform(replaceText("Pass1234"));

		ViewInteraction materialButton = onView(withId(R.id.button));
		materialButton.perform(click());

		ViewInteraction textView = onView(withId(R.id.textView));
		textView.check(matches(withText("You shall not pass!")));
	}

	/**
	 * Test case to verify password complexity when all requirements are met
	 */
	@Test
	public void testPasswordComplexEnough(){

		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ViewInteraction appCompatEditText = onView(withId(R.id.editText));
		appCompatEditText.perform(replaceText("Pass1234*") , closeSoftKeyboard());

		ViewInteraction materialButton = onView(withId(R.id.button));
		materialButton.perform(click());

		ViewInteraction textView = onView(withId(R.id.textView));
		textView.check(matches(withText("Your password meets the requirements")));
	}

	private static Matcher<View> childAtPosition(
			final Matcher<View> parentMatcher, final int position) {

		return new TypeSafeMatcher<View>() {
			@Override
			public void describeTo(Description description) {
				description.appendText("Child at position " + position + " in parent ");
				parentMatcher.describeTo(description);
			}

			@Override
			public boolean matchesSafely(View view) {
				ViewParent parent = view.getParent();
				return parent instanceof ViewGroup && parentMatcher.matches(parent)
						&& view.equals(((ViewGroup) parent).getChildAt(position));
			}
		};
	}
}
