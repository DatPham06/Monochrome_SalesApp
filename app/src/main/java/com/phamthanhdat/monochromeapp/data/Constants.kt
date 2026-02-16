package com.monochrome.app.data

import com.monochrome.app.model.Product

object Constants {
    val PRODUCTS = listOf(
        Product(
            id = "1",
            name = "Obsidian Trench",
            price = 295,
            category = "Outerwear",
            image = "https://picsum.photos/800/1000?grayscale&random=1",
            shortDescription = "A classic silhouette reimagined in deep matte black."
        ),
        Product(
            id = "2",
            name = "Marble Silk Shirt",
            price = 180,
            category = "Tops",
            image = "https://picsum.photos/800/1000?grayscale&random=2",
            shortDescription = "Pure silk with a subtle marble texture pattern."
        ),
        Product(
            id = "3",
            name = "Slate Trousers",
            price = 150,
            category = "Bottoms",
            image = "https://picsum.photos/800/1000?grayscale&random=3",
            shortDescription = "Tailored fit trousers for the modern minimalist."
        ),
        Product(
            id = "4",
            name = "Ebony Leather Tote",
            price = 450,
            category = "Accessories",
            image = "https://picsum.photos/800/1000?grayscale&random=4",
            shortDescription = "Full grain leather tote with silver hardware."
        ),
        Product(
            id = "5",
            name = "Ivory Knit Sweater",
            price = 220,
            category = "Tops",
            image = "https://picsum.photos/800/1000?grayscale&random=5",
            shortDescription = "Heavyweight cotton knit in a soft ivory hue."
        ),
        Product(
            id = "6",
            name = "Midnight Boots",
            price = 320,
            category = "Footwear",
            image = "https://picsum.photos/800/1000?grayscale&random=6",
            shortDescription = "Ankle boots crafted from premium calfskin."
        ),
        Product(
            id = "7",
            name = "Graphite Blazer",
            price = 380,
            category = "Outerwear",
            image = "https://picsum.photos/800/1000?grayscale&random=7",
            shortDescription = "Structured blazer suitable for day and night."
        ),
        Product(
            id = "8",
            name = "Onyx Pendant",
            price = 120,
            category = "Accessories",
            image = "https://picsum.photos/800/1000?grayscale&random=8",
            shortDescription = "A singular black onyx stone set in sterling silver."
        )
    )

    fun getSystemInstruction(): String {
        val productList = PRODUCTS.joinToString("\n") { 
            "- ${it.name} ($${it.price}): ${it.shortDescription}" 
        }
        
        return """
            You are "Mono", a high-end AI fashion stylist for the brand MONOCHROME.
            Our brand aesthetic is strictly black, white, and grey. Minimalist, avant-garde, and timeless.
            
            Your tone should be:
            - Sophisticated and elegant.
            - Concise and helpful.
            - Slightly mysterious but very polite.
            
            You have access to the following products in our catalog:
            $productList
            
            When a user asks about products, recommend items from this list.
            If asked about colors, remind them playfully that we only believe in the absence of color (black) and the combination of all colors (white).
        """.trimIndent()
    }
}
