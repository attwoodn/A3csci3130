package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.Button;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.CursorMatchers.withRowString;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Instrumentation test for adding, updating, and deleting business contacts.
 * Tests will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private String testBusinessName = "testBiz1023";
    private String testBusinessNumber = "123456789";
    private String testBusinessType = "Fisher";
    private String testProvince = "Nova Scotia";
    private String testAddress = "123 Test Street, Apt 520";

    private String testBusinessNameUpdate = "testBizniz10231023";
    private String testBusinessNumberUpdate = "1234567890";
    private String testBusinessTypeUpdate = "Processor";
    private String testProvinceUpdate = "New Brunswick";
    private String testAddressUpdate = "1234 Test Street, Apt 112";

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup(){
        mainActivityRule.getActivity();
        try{
            Thread.sleep(500);
        } catch (InterruptedException e){

        }
    }

    @Test
    public void addCheckUpdateDeleteNewContact(){
        // click on create contact button on main menu
        ViewInteraction createContactButton = onView(withId(R.id.createContactButton));
        createContactButton.perform(click());


        // create contact activity should have started. Get UI references
        ViewInteraction businessNameTextField = onView(withId(R.id.businessNameTextField));
        ViewInteraction businessNumberTextField = onView(withId(R.id.businessNumberTextField));
        ViewInteraction businessTypeSpinner = onView(withId(R.id.businessTypeSpinner));
        ViewInteraction provinceSpinner = onView(withId(R.id.provinceSpinner));
        ViewInteraction addressTextField = onView(withId(R.id.addressTextField));
        createContactButton = onView(withId(R.id.createContactButton));

        // set business name
        businessNameTextField.perform(typeText(testBusinessName));

        // set business number
        businessNumberTextField.perform(typeText(testBusinessNumber));

        // set business type and check selection
        businessTypeSpinner.perform(click());
        onData(allOf(is(instanceOf(String.class)), is(testBusinessType))).perform(click());
        businessTypeSpinner.check(matches(withSpinnerText(containsString(testBusinessType))));

        // set province and check selection
        provinceSpinner.perform(click());
        onData(allOf(is(instanceOf(String.class)), is(testProvince))).perform(click());
        provinceSpinner.check(matches(withSpinnerText(containsString(testProvince))));

        // set province
        addressTextField.perform(typeText(testAddress));
        closeSoftKeyboard();
        // submit contact to database and return to main activity
        createContactButton.perform(click());


        // should have returned to the main activity at this point
        // click on item in list that has the testBusinessName text to ensure it has been created successfully
        checkNewContact();

        pressBack();

        updateAndCheckNewContact();

        pressBack();


        boolean successfullyDeletedContact = false;
        try {
            deleteNewContact();
        } catch(NoMatchingViewException e){
            successfullyDeletedContact = true;
        }

        assertEquals(true, successfullyDeletedContact);
    }

    public void checkNewContact(){
        // click on item in list that has the testBusinessName text
        onView(allOf(withId(android.R.id.text1), withText(testBusinessName))).perform(click());

        // detail view activity should have started. Get UI references
        ViewInteraction businessNameTextField = onView(withId(R.id.businessNameTextField));
        ViewInteraction businessNumberTextField = onView(withId(R.id.businessNumberTextField));
        ViewInteraction businessTypeSpinner = onView(withId(R.id.businessTypeSpinner));
        ViewInteraction provinceSpinner = onView(withId(R.id.provinceSpinner));
        ViewInteraction addressTextField = onView(withId(R.id.addressTextField));

        // check that all the fields are the same as the created contact
        businessNameTextField.check(matches(withText(testBusinessName)));
        businessNumberTextField.check(matches(withText(testBusinessNumber)));
        businessTypeSpinner.check(matches(withSpinnerText(containsString(testBusinessType))));
        provinceSpinner.check(matches(withSpinnerText(containsString(testProvince))));
        addressTextField.check(matches(withText(testAddress)));
    }

    public void updateAndCheckNewContact(){
        // click on item in list that has the testBusinessName text
        onView(allOf(withId(android.R.id.text1), withText(testBusinessName))).perform(click());


        // detail view activity should have started. Get UI references
        ViewInteraction businessNameTextField = onView(withId(R.id.businessNameTextField));
        ViewInteraction businessNumberTextField = onView(withId(R.id.businessNumberTextField));
        ViewInteraction businessTypeSpinner = onView(withId(R.id.businessTypeSpinner));
        ViewInteraction provinceSpinner = onView(withId(R.id.provinceSpinner));
        ViewInteraction addressTextField = onView(withId(R.id.addressTextField));
        ViewInteraction updateContactButton = onView(withId(R.id.updateButton));

        // set business name
        businessNameTextField.perform(replaceText(testBusinessNameUpdate));

        // set business number
        businessNumberTextField.perform(replaceText(testBusinessNumberUpdate));

        // set business type and check selection
        businessTypeSpinner.perform(click());
        onData(allOf(is(instanceOf(String.class)), is(testBusinessTypeUpdate))).perform(click());
        businessTypeSpinner.check(matches(withSpinnerText(containsString(testBusinessTypeUpdate))));

        // set province and check selection
        provinceSpinner.perform(click());
        onData(allOf(is(instanceOf(String.class)), is(testProvinceUpdate))).perform(click());
        provinceSpinner.check(matches(withSpinnerText(containsString(testProvinceUpdate))));

        // set province
        addressTextField.perform(replaceText(testAddressUpdate));
        closeSoftKeyboard();

        // submit contact to database and return to main activity
        updateContactButton.perform(click());


        // main activity should have started
        // click on item in list that has the testBusinessNameUpdate text
        onView(allOf(withId(android.R.id.text1), withText(testBusinessNameUpdate))).perform(click());


        // detail view activity should have started
        // check that all the fields are the same as the created contact
        businessNameTextField.check(matches(withText(testBusinessNameUpdate)));
        businessNumberTextField.check(matches(withText(testBusinessNumberUpdate)));
        businessTypeSpinner.check(matches(withSpinnerText(containsString(testBusinessTypeUpdate))));
        provinceSpinner.check(matches(withSpinnerText(containsString(testProvinceUpdate))));
        addressTextField.check(matches(withText(testAddressUpdate)));
    }

    public void deleteNewContact(){
        // click on item in list that has the testBusinessNameUpdate text
        onView(allOf(withId(android.R.id.text1), withText(testBusinessNameUpdate))).perform(click());


        // detail view activity should have started
        // erase test contact
        onView(withId(R.id.deleteButton)).perform(click());


        // should have returned to the main activity at this point
        // click on item in list that has the testBusinessNameUpdate text. This should fail since the contact was deleted
        onView(allOf(withId(android.R.id.text1), withText(testBusinessNameUpdate))).perform(click());
    }
}
