package com.medicicineTrackingg.finalproject


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.content.pm.ActivityInfo
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import com.google.zxing.integration.android.IntentResult



class HomeFragment : Fragment() {

    private lateinit var btnScan: Button
    private lateinit var imageView: ImageView
    private lateinit var autoCompleteTextView: AutoCompleteTextView


    val medicines = arrayOf(
        Medicine(
            "Ibuprofen",
            "Ibuprofen is a nonsteroidal anti-inflammatory drug (NSAID) commonly used to relieve pain, reduce inflammation, and lower fever. It is effective in treating various conditions such as headache, muscle aches, menstrual cramps, and arthritis.",
            "200mg",
            "Take one to two tablets every 4 to 6 hours as needed. Do not exceed the recommended dosage.",
            "Possible side effects include stomach upset, heartburn, and dizziness. If you experience severe side effects, seek medical attention.",
            "Swallow the tablets with water. You can take them with or after food to minimize stomach upset.",
            "https://images.theconversation.com/files/321639/original/file-20200319-22610-18gca3.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=1200&h=1200.0&fit=crop"
        ),
        Medicine(
            "Ibunal",
            "Ibunal is a nonsteroidal anti-inflammatory drug (NSAID) commonly used to relieve " +
                    "pain, reduce inflammation, and lower fever. It is effective in treating various conditions such as headache, muscle aches, menstrual cramps, and arthritis.",
            "200mg",
            "Take one to two tablets every 4 to 6 hours as needed. Do not exceed the recommended dosage.",
            "Possible side effects include stomach upset, heartburn, and dizziness. If you experience severe side effects, seek medical attention.",
            "Swallow the tablets with water. You can take them with or after food to minimize stomach upset.",
            "https://oxyclinepharmacy.com.ng/wp-content/uploads/2022/05/IBUCAP-FORTE.jpg"
        ),
        Medicine(
            "Amoxicillin",
            "Amoxicillin is an antibiotic used to treat a variety of bacterial infections. It is commonly prescribed for respiratory tract infections, urinary tract infections, and skin infections. It works by stopping the growth of bacteria.",
            "500mg",
            "Take one capsule three times a day for the prescribed duration of treatment.",
            "Common side effects include diarrhea, nausea, and skin rash. If you experience severe or persistent side effects, contact your doctor.",
            "Take the capsule with or without food, as directed by your doctor.",
            "https://www.ddgroup.com/globalassets/productimages/naa019/naa019_1.jpg"
        ),
        Medicine(
            "Aspirin",
            "Aspirin is a common pain reliever and fever reducer. It belongs to the group of drugs called nonsteroidal anti-inflammatory drugs (NSAIDs). It can be used to relieve minor aches and pains, reduce fever, and help with inflammation.",
            "100mg",
            "Take once daily.",
            "May cause stomach irritation, heartburn, and allergic reactions such as rash or itching.",
            "Swallow with water.",
            "https://post.healthline.com/wp-content/uploads/2020/08/766x415_Is_Aspirin_an_NSAID.jpg"
        ),
        Medicine(
            "Loratadine",
            "Loratadine is an antihistamine used to relieve allergy symptoms such as runny nose, sneezing, itching, and watery eyes. It provides relief from seasonal allergies and allergic reactions.",
            "10mg",
            "Take one tablet once daily. Do not exceed the recommended dosage.",
            "Common side effects include drowsiness, dry mouth, and headache. These side effects are usually mild and go away on their own.",
            "Swallow the tablet with water. You can take it with or without food.",
            "https://www.soin-et-nature.com/36840-menu_default/mylan-viatris-council-loratadina-10-mg-alergia-7-comprimidos.jpg"
        ),
        Medicine(
            "Metformin",
            "Metformin is an oral medication used to manage type 2 diabetes. It helps control blood sugar levels by improving insulin sensitivity and reducing glucose production in the liver.",
            "500mg",
            "Take one tablet twice daily with meals. Follow your doctor's instructions and monitor your blood sugar regularly.",
            "Common side effects include gastrointestinal discomfort, such as diarrhea and nausea. If these side effects persist or worsen, consult your doctor.",
            "Swallow the tablet with a full glass of water. Do not crush or chew the tablet.",
            "https://cdn-prod.medicalnewstoday.com/content/images/articles/323/323128/metformin-tablet-box.jpg"
        ),
        Medicine(
            "Simvastatin",
            "Simvastatin is a medication used to lower cholesterol levels in the blood. It belongs to a class of drugs known as statins and helps reduce the risk of heart disease and stroke.",
            "20mg",
            "Take one tablet in the evening, with or without food. Follow your doctor's instructions and maintain a healthy lifestyle.",
            "Common side effects include muscle pain, headache, and stomach upset. Rarely, it may cause liver problems or muscle damage. If you experience severe side effects, seek medical attention.",
            "Swallow the tablet whole with water. Avoid consuming grapefruit or grapefruit juice while taking this medication.",
            "https://www.crescentpharma.com/wp-content/uploads/2016/01/Simvastatin-20mg-Tablets_28s-2.png"
        ),
        Medicine(
            "Acetaminophen",
            "Acetaminophen is a commonly used pain reliever and fever reducer. It is effective in reducing mild to moderate pain and fever, but it does not have significant anti-inflammatory properties.",
            "500mg",
            "Take one to two tablets every 4 to 6 hours as needed. Do not exceed the recommended daily dosage.",
            "When taken as directed, acetaminophen is generally well-tolerated. However, taking too much can cause liver damage. Follow the recommended dosage and avoid combining with other medications containing acetaminophen.",
            "Swallow the tablets with water. You can take them with or without food.",
            "https://www.drugwatch.com/wp-content/uploads/Acetaminophen.jpg"
        ),
        Medicine(
            "Omeprazole",
            "Omeprazole is a proton pump inhibitor (PPI) used to reduce stomach acid production. It is commonly prescribed to treat conditions such as gastroesophageal reflux disease (GERD), stomach ulcers, and heartburn.",
            "20mg",
            "Take one capsule daily in the morning, at least 30 minutes before breakfast. Swallow the capsule whole; do not crush or chew it.",
            "Common side effects include headache, diarrhea, and stomach pain. If you experience severe side effects or signs of an allergic reaction, seek medical attention.",
            "Swallow the capsule whole with water. Do not crush or chew it. If you have difficulty swallowing, you can open the capsule and sprinkle the contents on applesauce, then swallow immediately without chewing.",
            "https://i0.wp.com/post.medicalnewstoday.com/wp-content/uploads/sites/3/2019/07/a-packet-of-omeprazole-image-credit-siufaiho-2006.jpg?w=1155&h=1730"
        ),
        Medicine(
            "Sertraline",
            "Sertraline is an antidepressant medication that belongs to the selective serotonin reuptake inhibitor (SSRI) class. It is used to treat depression, obsessive-compulsive disorder (OCD), panic disorder, and social anxiety disorder.",
            "50mg",
            "Take one tablet daily, in the morning or evening. Follow your doctor's instructions and allow several weeks for the medication to take full effect.",
            "Common side effects include nausea, diarrhea, and sleep disturbances. If you experience worsening depression, suicidal thoughts, or severe side effects, seek immediate medical attention.",
            "Swallow the tablet with water. You can take it with or without food.",
            "https://5.imimg.com/data5/SELLER/Default/2022/12/VP/NI/SS/23808697/sertraline-hcl-zoloft-100-mg-tablet-guaranteed-delivery-usa.png"
        ),
        Medicine(
            "Metoprolol",
            "Metoprolol is a beta-blocker used to treat high blood pressure, angina (chest pain), and heart failure. It works by reducing the workload on the heart and helping it beat more regularly.",
            "25mg",
            "Take one tablet twice daily, with or without food. Do not skip doses or stop taking the medication without consulting your doctor.",
            "Common side effects include tiredness, dizziness, and slow heartbeat. If you experience severe side effects or signs of an allergic reaction, seek medical attention.",
            "Swallow the tablet whole with water. Do not crush or chew it.",
            "https://www.mintrxpharmacy.com/on/demandware.static/-/Sites-master-catalog/default/dw03fb3d95/images/products/metp-0100-01-gen-01.jpg"
        ),
        Medicine(
        "Metoprolol",
        "Metoprolol is a beta-blocker used to treat high blood pressure, angina (chest pain), and heart failure. It works by reducing the workload on the heart and helping it beat more regularly.",
        "25mg",
        "Take one tablet twice daily, with or without food. Do not skip doses or stop taking the medication without consulting your doctor.",
        "Common side effects include tiredness, dizziness, and slow heartbeat. If you experience severe side effects or signs of an allergic reaction, seek medical attention.",
        "Swallow the tablet whole with water. Do not crush or chew it.",
        "https://www.mintrxpharmacy.com/on/demandware.static/-/Sites-master-catalog/default/dw03fb3d95/images/products/metp-0100-01-gen-01.jpg"
    )
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        imageView = view.findViewById(R.id.imageView)
        btnScan = view.findViewById(R.id.btn_scan)

        btnScan.setOnClickListener {
            scanCode()
        }
        autoCompleteTextView = view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, medicines.map { it.name })
        autoCompleteTextView.setAdapter(adapter)

        val textViewNotFound = view.findViewById<TextView>(R.id.textViewNotFound)

        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            textViewNotFound?.visibility = TextView.GONE
            val selectedMedicineName = adapter.getItem(position)
            val selectedMedicine = medicines.find { it.name.equals(selectedMedicineName, ignoreCase = true) }
            selectedMedicine?.let {
                openDetailsActivity(it)
            }
        }

        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val enteredText = s.toString()
                val medicine = medicines.find { it.name.equals(enteredText, ignoreCase = true) }

                if (medicine != null) {
                    openDetailsActivity(medicine)
                } else {
                    textViewNotFound?.visibility = if (enteredText.isNotBlank()) TextView.VISIBLE else TextView.GONE
                }
            }
        })

        return view
    }

    private fun scanCode() {
        val integrator = IntentIntegrator.forSupportFragment(this)
        integrator.setPrompt("Volume up to set to flash on")
        integrator.setBeepEnabled(true)
        integrator.setOrientationLocked(true)
        integrator.setCaptureActivity(CaptureActivity::class.java)
        integrator.initiateScan()
    }

    private fun showResult(contents: String) {

        autoCompleteTextView.setText(contents)
          }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult? =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                showResult(result.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun openDetailsActivity(medicine: Medicine) {
        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.EXTRA_MEDICINE, medicine)
        startActivity(intent)
    }
}