/**
 * 
 */
 
 /** Variables */
        let files = []
        let dragArea = document.querySelector('.drag-area')
        let input = document.querySelector('.drag-area input')
        let button = document.querySelector('.card button')
        let select = document.querySelector('.drag-area .select')
        let container = document.querySelector('.imageContainer')

        /** CLICK LISTENER */
        select.addEventListener('click', () => input.click());

        /* INPUT CHANGE EVENT */
        input.addEventListener('change', () => {
            let file = input.files;

            // if user select no image
            if (file.length == 0) return;

            for (let i = 0; i < file.length; i++) {
                if (file[i].type.split("/")[0] != 'image') continue;
                if (!files.some(e => e.name == file[i].name)) files.push(file[i])
            }

            showImages();
        });

        /** SHOW IMAGES */
        function showImages() {
            container.innerHTML = files.reduce((prev, curr, index) => {
                return `${prev}
                        <div class="image">
                            <span onclick="deleteImage(${index})">&times;</span>
                            <img src="${URL.createObjectURL(curr)}" />
                        </div>`
            }, '');
        }

        /* DELETE IMAGE */
        function deleteImage(index) {
            files.splice(index, 1);
            showImages();
        }

        /* DRAG & DROP */
        dragArea.addEventListener('dragover', e => {
            e.preventDefault()
            dragArea.classList.add('dragover')
        })

        /* DRAG LEAVE */
        dragArea.addEventListener('dragleave', e => {
            e.preventDefault()
            dragArea.classList.remove('dragover')
        });

        /* DROP EVENT */
        dragArea.addEventListener('drop', e => {
            e.preventDefault()
            dragArea.classList.remove('dragover');

            let file = e.dataTransfer.files;
            for (let i = 0; i < file.length; i++) {
                /** Check selected file is image */
                if (file[i].type.split("/")[0] != 'image') continue;

                if (!files.some(e => e.name == file[i].name)) files.push(file[i])
            }
            showImages();
        });