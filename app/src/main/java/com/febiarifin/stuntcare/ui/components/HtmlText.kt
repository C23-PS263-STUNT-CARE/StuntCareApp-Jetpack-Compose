package com.febiarifin.stuntcare.ui.components

import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme

@Composable
fun HtmlText(html: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context -> TextView(context) },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}

@Preview
@Composable
fun PreviewHtmlText() {
    StuntCareTheme {
        HtmlText(
            "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam cursus suscipit aliquam. Aliquam auctor sed quam quis varius. Suspendisse eget tincidunt lorem, id eleifend dolor. Integer ornare sagittis neque et pretium.</p>" +
                    "<ul>" +
                    "    <li>Lorem ipsum dolor sit amet, consectetur adipiscing elit.&nbsp;</li>" +
                    "    <li>Nullam cursus suscipit aliquam.&nbsp;</li>" +
                    "    <li>Aliquam auctor sed quam quis varius.&nbsp;</li>" +
                    "    <li>Suspendisse eget tincidunt lorem, id eleifend dolor.&nbsp;</li>" +
                    "    <li>Integer ornare sagittis neque et pretium.</li>" +
                    "</ul>"
        )
    }
}