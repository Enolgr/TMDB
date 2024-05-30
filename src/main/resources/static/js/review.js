// AÑADIR REVIEWS

// add movie details form
// be sure that the ids of the input elements in the html is the same used here
function addReserve() {
    const texto = document.getElementById('texto').value;
    const imbdId = document.getElementById('imdbId').value;

    // create the object that is defined in your model, use the correct properties
    const review = {
        reviewBody: texto,
        imdbId: imbdId,
    };

    // this route will send the request to the endpoint under "@PostMapping("/add")", modify it if needed
    fetch('/api/peliculas/reviews', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(review)
    }).then(response => {
        // if the response is ok reload the page to get the new elements
        // if not, show an alert with the error
        if (response.ok) {
            window.location.reload();
        } else {
            return response.text().then(text => {
                alert("Error: " + text);
            });
        }
    }).catch(error => {
        alert("Error: " + error);
    });
}

// MOSTRAR REVIEWS
document.addEventListener('DOMContentLoaded', function() {
    const id = document.getElementById("imdbId").value;

    fetch('http://localhost:8080/api/peliculas/reviews/' + id)
        .then(response => response.json())
        .then(data => {
            const pelicula = data.pelicula; // Obtenemos la película para usar su título

            // Crear y agregar el título de la película fuera del contenedor de reseñas
            // Agregar el título de la película dentro de un div con id "h1-titulo"
            const h1TituloContainer = document.createElement('div');
            h1TituloContainer.id = 'h1-titulo';
            const tituloElement = document.createElement('h1');
            tituloElement.textContent = pelicula.title;
            h1TituloContainer.appendChild(tituloElement);

            // Agregar el contenedor del título antes del contenedor de reseñas
            document.body.insertBefore(h1TituloContainer, document.getElementById('reviews'));

            // Crear un contenedor para la información de la película (póster, géneros, carrusel)
            const infoContainer = document.createElement('div');
            infoContainer.classList.add('info-container');

            const posterContainer = document.createElement('div');
            posterContainer.classList.add('contenedor');

            // Agregar el póster de la película
            const posterElement = document.createElement('img');
            posterElement.className = 'poster';
            posterElement.src = pelicula.poster;
            posterElement.alt = `${pelicula.title} Poster`;
            posterContainer.appendChild(posterElement);

            // Crear el carrusel de imágenes de fondo
            const carouselContainer = document.createElement('div');
            carouselContainer.classList.add('carousel');
            const carouselTrack = document.createElement('div');
            carouselTrack.classList.add('carousel-track');
            pelicula.backdrops.forEach(url => {
                const imgElement = document.createElement('img');
                imgElement.src = url;
                imgElement.classList.add('carousel-item');
                carouselTrack.appendChild(imgElement);
            });
            carouselContainer.appendChild(carouselTrack);
            const prevButton = document.createElement('button');
            prevButton.classList.add('carousel-button', 'prev-button');
            prevButton.textContent = '<';
            const nextButton = document.createElement('button');
            nextButton.classList.add('carousel-button', 'next-button');
            nextButton.textContent = '>';
            const buttonsContainer = document.createElement('div');
            buttonsContainer.classList.add('carousel-buttons');
            buttonsContainer.appendChild(prevButton);
            buttonsContainer.appendChild(nextButton);
            carouselContainer.appendChild(buttonsContainer);

            // Agregar el carrusel al contenedor de información
            infoContainer.appendChild(posterElement);
            infoContainer.appendChild(carouselContainer);

            // Agregar los géneros como cajas de texto
            const genresContainer = document.createElement('div');
            genresContainer.classList.add('genres');
            pelicula.genres.forEach(genre => {
                const genreElement = document.createElement('span');
                genreElement.classList.add('genre');
                genreElement.textContent = genre;
                genresContainer.appendChild(genreElement);
            });

            // Agregar los géneros al contenedor de información, después del carrusel
            infoContainer.appendChild(genresContainer);

            // Crear y agregar el contenedor de reseñas dentro de info-container
            const divReviews = document.createElement('div');
            divReviews.id = 'div-reviews';
            divReviews.classList.add('div-reviews');
            infoContainer.appendChild(divReviews);

            // Agregar el contenedor de información antes del contenedor de reseñas
            document.body.insertBefore(infoContainer, document.getElementById('reviews'));

            let currentIndex = 0;

            function updateCarousel() {
                const offset = -currentIndex * 100;
                carouselTrack.style.transform = `translateX(${offset}%)`;
            }

            prevButton.addEventListener('click', () => {
                if (currentIndex > 0) {
                    currentIndex--;
                } else {
                    currentIndex = pelicula.backdrops.length - 1; // Va al último
                }
                updateCarousel();
            });

            nextButton.addEventListener('click', () => {
                if (currentIndex < pelicula.backdrops.length - 1) {
                    currentIndex++;
                } else {
                    currentIndex = 0; // Va al primero
                }
                updateCarousel();
            });

            updateCarousel();

            // Agregar las reseñas dentro de div-reviews
            data.reviews.forEach(review => {
                const reviewElement = document.createElement('div');
                reviewElement.classList.add('review');

                const bodyElement = document.createElement('p');
                bodyElement.textContent = `Review: ${review.body}`;
                bodyElement.classList.add('review-titulo');
                bodyElement.style.color = 'white';
                const dateElement = document.createElement('p');

                const date = new Date(review.id_.date);
                const hours = date.getHours().toString().padStart(2, '0');
                const minutes = date.getMinutes().toString().padStart(2, '0');
                dateElement.textContent = `${hours}:${minutes}`;

                reviewElement.appendChild(bodyElement);
                reviewElement.appendChild(dateElement);
                divReviews.appendChild(reviewElement); // Añadir las reseñas al contenedor div-reviews
            });
        })
        .catch(error => console.error('Error:', error));

    // botón
    const submitbutton = document.getElementById("boton");
    submitbutton.addEventListener('click', function(event) {
        event.preventDefault();
        addReserve();
    });
});
