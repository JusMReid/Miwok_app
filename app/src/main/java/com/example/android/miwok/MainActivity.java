/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        FragmentAdapter adapter = new FragmentAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.categogryTabs);

        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);


        /**
        // Find the View that shows the numbers category
        TextView numbers = (TextView) findViewById(R.id.numbers);
        TextView phrases = (TextView) findViewById(R.id.phrases);
        TextView family = (TextView) findViewById(R.id.family);
        TextView colors = (TextView) findViewById(R.id.colors);


         // Set a click listener on that View
         numbers.setOnClickListener(new View.OnClickListener() {
         // The code in this method will be executed when the numbers View is clicked on.
         @Override public void onClick(View view) {
         Toast.makeText(MainActivity.this, getString(R.string.numbersToastMessage), Toast.LENGTH_SHORT).show();
         Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
         startActivity(numbersIntent);
         }
         });

         // Set a click listener on that View
         phrases.setOnClickListener(new View.OnClickListener() {
         // The code in this method will be executed when the phrases View is clicked on.
         @Override public void onClick(View view) {
         Toast.makeText(MainActivity.this, getString(R.string.phrasesToastMessage), Toast.LENGTH_SHORT).show();
         Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
         startActivity(phrasesIntent);
         }
         });

         // Set a click listener on that View
         family.setOnClickListener(new View.OnClickListener() {
         // The code in this method will be executed when the family View is clicked on.
         @Override public void onClick(View view) {
         Toast.makeText(MainActivity.this, getString(R.string.familyToastMessage), Toast.LENGTH_SHORT).show();
         Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
         startActivity(familyIntent);
         }
         });

         // Set a click listener on that View
         colors.setOnClickListener(new View.OnClickListener() {
         // The code in this method will be executed when the colors View is clicked on.
         @Override public void onClick(View view) {
         Toast.makeText(MainActivity.this, getString(R.string.colorsToastMessage), Toast.LENGTH_SHORT).show();
         Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
         startActivity(colorsIntent);
         }
         });
         **/


    }
}
