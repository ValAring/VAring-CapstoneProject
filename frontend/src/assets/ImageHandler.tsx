



export function resizeImage(file: File, callback: (resizedFile: File) => void){
    const reader = new FileReader();
    reader.onload = (e) => {
        if (!e.target) {
            console.error("FileReader's target is null");
            return;
        }

        const img = new Image();
        img.src = e.target.result as string;
        img.onload = () => {
            const canvas = document.createElement("canvas");
            const ctx = canvas.getContext("2d");

            if (!ctx) {
                console.error("Canvas context is null");
                return;
            }

            const maxWidth = 800;
            const maxHeight = 800;
            let width = img.width;
            let height = img.height;

            width = width > height ? (width > maxWidth ? maxWidth : width) : width;
            height = height > width ? (height > maxHeight ? maxHeight : height) : height;

            canvas.width = width;
            canvas.height = height;
            ctx.drawImage(img, 0, 0, width, height);

            canvas.toBlob((blob) => {
                if (blob) {
                    const resizedFile = new File([blob], file.name, { type: "image/jpeg" });
                    callback(resizedFile);
                } else {
                    console.error("Error converting canvas to blob");
                }
            }, "image/jpeg");
        };
    };
    reader.readAsDataURL(file);
}